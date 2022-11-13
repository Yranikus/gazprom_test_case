package com.example.gazprom_test_case.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Value("#{propertiesfilemapping['vk.validation.error']}")
    private String validation_error;

    public boolean validateCurrentId(String id){
        if (id == null) return false;
        if (id.matches("^\\d+$")) return true;
        if (id.matches("^\\..+")) return false;
        if (id.length() < 5 || id.length() > 32) return false;
        if (id.matches("^\\d{3,}\\D+")) return false;
        if (id.contains("admin") || id.contains("photo") || id.contains("event")) return false;
        if (id.matches("^_.+_$")) return false;
        if (id.matches("^.+\\..+")) {
            if (!id.matches("^.+\\.[a-zA-Z].{3,}")) return false;
        }
        if (id.matches(".+\s.+")) return false;
        return true;
    }

    public ResponseEntity validateBothIds(String user_id,String group_id){
        if (!validateCurrentId(user_id)){
            return new ResponseEntity("Неверный user_id\n" + validation_error, HttpStatus.BAD_REQUEST);
        }
        if (!validateCurrentId(group_id)){
            return new ResponseEntity("Неверный group_id\n" + validation_error,HttpStatus.BAD_REQUEST);
        }
        return null;
    }


}
