package com.example.gazprom_test_case.controllers;

import com.example.gazprom_test_case.entities.RequestEntity;
import com.example.gazprom_test_case.service.MembrshipCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class MembershipController {

    @Autowired
    private MembrshipCheckService validationService;


    @PostMapping(value = "/{vk_service_token}" )
    public ResponseEntity checkMembership(@RequestBody RequestEntity requestEntity, @PathVariable String vk_service_token) {
        return validationService.checkMembership(requestEntity.getUser_id(),requestEntity.getGroup_id(),vk_service_token);
    }


}
