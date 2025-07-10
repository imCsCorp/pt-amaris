package com.camilosoto.prueba_tecnica.domain.services;

import com.camilosoto.prueba_tecnica.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class NotificationService {
    @Autowired
    private SnsClient snsClient;
    @Autowired
    private EmailService emailService;

    public void notify(User user, String message, String type) {
        if ("sms".equalsIgnoreCase(type) && user.getPhone() != null) {
            sendSms(user.getPhone(), message);
        } else if ("email".equalsIgnoreCase(type) && user.getEmail() != null) {
            emailService.sendEmail(user, "Suscripción a fondo", message);
        }
    }

    private void sendSms(String phoneNumber, String message) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();
        snsClient.publish(request);
    }
}
