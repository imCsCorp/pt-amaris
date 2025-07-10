package com.camilosoto.prueba_tecnica.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {
    @Bean
    public SnsClient snsClient() {
        return SnsClient.create();
    }
}
