package com.felipecamatta.archref.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationConfig {

    @Value("${queue.person-created}")
    private String personCreatedQueue;

    @Value("${character-service.url}")
    private String characterServiceUrl;

}
