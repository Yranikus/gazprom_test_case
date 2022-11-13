package com.example.gazprom_test_case.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;



import java.time.Duration;
import java.util.Locale;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder
                .setConnectTimeout(Duration.ofMillis(60000))
                .setReadTimeout(Duration.ofMillis(60000))
                .build();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public HttpHeaders acceptedLanguageHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        String headerValue = "ru-RU, ru;q=0.9, en-US;q=0.8, en;q=0.7, fr;q=0.6";
        httpHeaders.setAcceptLanguage(Locale.LanguageRange.parse(headerValue));
        return httpHeaders;
    }


    @Bean
    public HttpEntity httpEntityWithRussianLangHeaders(HttpHeaders acceptedLanguageHeaders) {
        return new HttpEntity(null, acceptedLanguageHeaders);
    }

}
