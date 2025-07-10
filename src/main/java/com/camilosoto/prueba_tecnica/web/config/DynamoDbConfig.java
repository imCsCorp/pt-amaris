package com.camilosoto.prueba_tecnica.web.config;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import com.camilosoto.prueba_tecnica.persistence.models.User;
import com.camilosoto.prueba_tecnica.persistence.models.Fund;
import com.camilosoto.prueba_tecnica.persistence.models.Transaction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;


@Configuration
public class DynamoDbConfig {
    @Value("${aws.access-key}")
    private String accessKey;

    @Value("${aws.secret-key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                )
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    public DynamoDbTable<User> userTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table("User", TableSchema.fromBean(User.class));
    }

    @Bean
    public DynamoDbTable<Fund> fundTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table("Fund", TableSchema.fromBean(Fund.class));
    }

    @Bean
    public DynamoDbTable<Transaction> transactionTable(DynamoDbEnhancedClient enhancedClient) {
        return enhancedClient.table("Transaction", TableSchema.fromBean(Transaction.class));
    }
}
