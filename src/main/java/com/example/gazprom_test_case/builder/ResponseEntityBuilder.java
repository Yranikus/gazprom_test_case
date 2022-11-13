package com.example.gazprom_test_case.builder;

import com.example.gazprom_test_case.entities.ResponceToClientEntity;
import com.example.gazprom_test_case.entities.Vk_user;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityBuilder {

    public ResponseEntity<ResponceToClientEntity> getResponse(Vk_user vk_user, String isMember_response){
        int status = Integer.parseInt(isMember_response.split(":")[1].split("}")[0]);
        ResponceToClientEntity responceToClientEntity = new ResponceToClientEntity();
        responceToClientEntity.setFirst_name(vk_user.getFirst_name());
        responceToClientEntity.setLast_name(vk_user.getLast_name());
        responceToClientEntity.setMiddle_name(vk_user.getNickname());
        responceToClientEntity.setMember(status != 0);
        return new ResponseEntity<>(responceToClientEntity, HttpStatus.ACCEPTED);
    }

}
