package com.camilosoto.prueba_tecnica.persistence.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Transaction {

    public Transaction(String id, String userId, Integer fundId, String type, Integer amount, String date) {
        this.id = id;
        this.userId = userId;
        this.fundId = fundId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Transaction() {
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
