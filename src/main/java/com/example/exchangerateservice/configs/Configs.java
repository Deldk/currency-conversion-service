package com.example.exchangerateservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Configs {

    /**
     * This is the rest client configuration used to communicate with the external api
     * @param baseUrl
     */
    @Bean
    public RestClient restClient(@Value("${exchange.api.url}") String baseUrl){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "application.json")
                .build();
    }
}
