package com.ayi.rest.serv.app.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = {
        "com.ayi.rest.serv.app.configurations" +
        "com.ayi.rest.serv.app.services" +
        "com.ayi.rest.serv.app.mappers" +
        "com.ayi.rest.serv.app.repositories"
})
public class CommonConfigurations {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}