package com.example.gazprom_test_case;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableCaching
public class GazpromTestCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GazpromTestCaseApplication.class, args);
	}

}
