package com.example.gazprom_test_case.service;


import com.example.gazprom_test_case.builder.ResponseEntityBuilder;
import com.example.gazprom_test_case.entities.ResponseFromVKAPIEntity;
import com.example.gazprom_test_case.entities.vkerrors.ErrorEntity;
import com.example.gazprom_test_case.entities.Vk_user;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class MembrshipCheckService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ResponseEntityBuilder responseBuilder;

    @Autowired
    private HttpEntity httpEntityWithRussianLangHeaders;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Value("#{propertiesfilemapping['vk.url_request']}")
    private String url;
    @Value("#{propertiesfilemapping['vk.method.get_user']}")
    private String method_get_user;
    @Value("#{propertiesfilemapping['vk.params.get_user']}")
    private String params_get_user;
    @Value("#{propertiesfilemapping['vk.version']}")
    private String vk_version;
    @Value("#{propertiesfilemapping['vk.user.not_found.error']}")
    private String user_not_found;
    @Value("#{propertiesfilemapping['vk.method.isMember']}")
    private String method_isMember;
    @Value("#{propertiesfilemapping['vk.params.isMember']}")
    private String parems_isMember;
    @Value("#{propertiesfilemapping['vk.access_token']}")
    private String access_token;


    private ResponseEntity getResponseFromGetUserRequest(String user_id, String vk_acces_key){

        ResponseEntity response = restTemplate.exchange(
                url + method_get_user +
                user_id + params_get_user +
                vk_acces_key + vk_version, HttpMethod.GET, httpEntityWithRussianLangHeaders,
                String.class);
        return response;
    }

    private ResponseEntity getMembership(int user_id, String group_id, String vk_acces_key){
        ResponseEntity response = restTemplate.getForEntity(
                url + method_isMember +
                        group_id + parems_isMember + user_id + access_token +
                        vk_acces_key + vk_version,
                String.class);
        return response;
    }

    private ResponseEntity checkResponseForErrors(String response){
        try {
            ErrorEntity error = objectMapper.readValue(response,ErrorEntity.class);
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private Vk_user getVkUserEntityFromResponse(String responce){

        Vk_user vk_user = null;
        try {
            ResponseFromVKAPIEntity<Vk_user> vk_users = objectMapper.readValue(responce,new TypeReference<ResponseFromVKAPIEntity<Vk_user>>(){});
            if (vk_users.getResponse().size() != 0) vk_user = vk_users.getResponse().get(0) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return vk_user;
    }



    @Cacheable("data")
    public ResponseEntity checkMembership(String user_id,String group_id, String vk_acces_key){
        ResponseEntity validation_error = validationService.validateBothIds(user_id,group_id);
        if (validation_error != null){
            return validation_error;
        }
        String get_user_responce = getResponseFromGetUserRequest(user_id,vk_acces_key).getBody().toString();
        ResponseEntity error_response = checkResponseForErrors(get_user_responce);
        if (error_response != null) {
            return error_response;
        }
        Vk_user vk_user = getVkUserEntityFromResponse(get_user_responce);
        if (vk_user == null) {
            return  new ResponseEntity<>(user_not_found,HttpStatus.BAD_REQUEST);
        }
        String isMember_response = getMembership(vk_user.getId(),group_id,vk_acces_key).getBody().toString();
        error_response = checkResponseForErrors(isMember_response);
        if (error_response != null) {
            return error_response;
        }
        return responseBuilder.getResponse(vk_user,isMember_response);
    }

}

