package com.camilosoto.prueba_tecnica.persistence.models;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class Transaction {
    private String id;
    private String userId;
    private Integer fundId;
    private String type; // SUBSCRIBE or CANCEL
    private Integer amount;
    private String date;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
