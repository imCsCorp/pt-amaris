package com.camilosoto.prueba_tecnica.persistence.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Fund {
    public Fund(Integer id, String name, Integer minimumAmount, String category) {
        this.id = id;
        this.name = name;
        this.minimumAmount = minimumAmount;
        this.category = category;
    }

    public Fund() {
    }

    private Integer id;
    private String name;
    private Integer minimumAmount;
    private String category;

    @DynamoDbPartitionKey
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Integer minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
