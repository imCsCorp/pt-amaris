package com.camilosoto.prueba_tecnica.persistence.models;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.util.UUID;

@Data
@DynamoDbBean
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private Integer balance;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
