package com.camilosoto.prueba_tecnica.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
public class SesConfig {
    @Bean
    public SesClient sesClient() {
        return SesClient.create();
    }
}
