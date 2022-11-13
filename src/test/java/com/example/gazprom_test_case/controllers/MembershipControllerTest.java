package com.example.gazprom_test_case.controllers;

import com.example.gazprom_test_case.configuration.auth.TestSecurityConfig;
import com.example.gazprom_test_case.entities.RequestEntity;
import com.example.gazprom_test_case.entities.ResponceToClientEntity;
import com.example.gazprom_test_case.entities.vkerrors.ErrorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = TestSecurityConfig.class)
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc(addFilters = false)
class MembershipControllerTest {

    @Autowired
    private RestTemplate restTemplate;


    private static HttpHeaders headers = new HttpHeaders();

    @Value("${url}")
    private String url;
    @Value("${valid_token}")
    private String valid_token;
    @Value("${invalid_token}")
    private String invalid_token;
    @Value("${valid_userid}")
    private String valid_userid;
    @Value("${invalid_userid}")
    private String invalid_userid;
    @Value("${valid_groupid}")
    private String valid_groupid;
    @Value("${invalid_token_msg}")
    private String invalid_token_msg;
    @Value("${second_valid_group_id}")
    private String second_valid_group_id;
    @Value("${non_existent_user}")
    private String non_existent_user;


    @BeforeAll
    static void setHeaders(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }


    //Проверка на ввод не валидного ключа
    @Test
    void InvalidTokenTest() {
        RequestEntity requestEntity = new RequestEntity(valid_userid,valid_groupid);
        HttpEntity<RequestEntity> entity  = new HttpEntity<>(requestEntity,headers);
        ResponseEntity<ErrorEntity> errorEntity = restTemplate.exchange(url + invalid_token, HttpMethod.POST, entity, ErrorEntity.class);
        Assertions.assertEquals(invalid_token_msg, errorEntity.getBody().getError().getError_msg());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorEntity.getStatusCode());
    }

    //Проверка на выдачу ошибки при вводе некорктного user_ID
    @Test
    void checkInvalidUserIDTest() {
        RequestEntity requestEntity = new RequestEntity(invalid_userid,valid_groupid);
        HttpEntity<RequestEntity> entity  = new HttpEntity<>(requestEntity,headers);
        ResponseEntity<String> errorEntity = restTemplate.exchange(url + invalid_token, HttpMethod.POST, entity, String.class);
        Assertions.assertTrue(errorEntity.getBody().contains("Неверный user_id"));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, errorEntity.getStatusCode());
    }

    //Проверка на то что пользователь действительно состоит в группе. В этом тесте я вбил свой ник и паблик в котором я сстою.
    @Test
    void userIsMemberTest() {
        RequestEntity requestEntity = new RequestEntity(valid_userid,valid_groupid);
        HttpEntity<RequestEntity> entity  = new HttpEntity<>(requestEntity,headers);
        ResponseEntity<ResponceToClientEntity> errorEntity = restTemplate.exchange(url + valid_token, HttpMethod.POST, entity, ResponceToClientEntity.class);
        ResponceToClientEntity correct_response = new ResponceToClientEntity("Салеев","Коля", "",true);
        Assertions.assertTrue(errorEntity.getBody().isMember());
        Assertions.assertEquals(HttpStatus.ACCEPTED,errorEntity.getStatusCode());
        Assertions.assertEquals(correct_response.getFirst_name(), errorEntity.getBody().getFirst_name());
        Assertions.assertEquals(correct_response.getLast_name(), errorEntity.getBody().getLast_name());
    }

    //Проверка на то что пользователь не состоит в группе. Так же использовал свой ник и паблик в котором не состою
    @Test
    void userIsNotMemberTest() {
        RequestEntity requestEntity = new RequestEntity(valid_userid,second_valid_group_id);
        HttpEntity<RequestEntity> entity  = new HttpEntity<>(requestEntity,headers);
        ResponseEntity<ResponceToClientEntity> errorEntity = restTemplate.exchange(url + valid_token, HttpMethod.POST, entity, ResponceToClientEntity.class);
        ResponceToClientEntity correct_response = new ResponceToClientEntity("Салеев","Коля", "",true);
        Assertions.assertFalse(errorEntity.getBody().isMember());
        Assertions.assertEquals(HttpStatus.ACCEPTED,errorEntity.getStatusCode());
        Assertions.assertEquals(correct_response.getFirst_name(), errorEntity.getBody().getFirst_name());
        Assertions.assertEquals(correct_response.getLast_name(), errorEntity.getBody().getLast_name());
    }

    //Проверка на то что при вбивании несуществующего userID вылетит ошибка.
    @Test
    void userNotFoundTest() {
        RequestEntity requestEntity = new RequestEntity(non_existent_user,second_valid_group_id);
        HttpEntity<RequestEntity> entity  = new HttpEntity<>(requestEntity,headers);
        ResponseEntity<String> errorEntity = restTemplate.exchange(url + valid_token, HttpMethod.POST, entity, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,errorEntity.getStatusCode());
        Assertions.assertEquals("Пользователь не найден", errorEntity.getBody());
    }


}