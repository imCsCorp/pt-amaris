package com.camilosoto.prueba_tecnica.persistence.models;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@DynamoDbBean
public class Fund {

    private Integer id;
    private String name;
    private Integer minimumAmount;
    private String category;

    @DynamoDbPartitionKey
    public Integer getId() {
        return id;
    }
}
