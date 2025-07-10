package com.camilosoto.prueba_tecnica.web.dto;

public class FundActionRequest {
    public FundActionRequest() {
    }

    public FundActionRequest(String userId, Integer fundId, String notificationType) {
        this.userId = userId;
        this.fundId = fundId;
        this.notificationType = notificationType;
    }

    private String userId;
    private Integer fundId;
    private String notificationType;

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

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
