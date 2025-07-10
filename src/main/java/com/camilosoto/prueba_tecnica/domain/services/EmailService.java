package com.camilosoto.prueba_tecnica.domain.services;

import com.camilosoto.prueba_tecnica.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailService {
    @Autowired
    private SesClient sesClient;

    public void sendEmail(User user, String subject, String bodyText) {
        Destination destination = Destination.builder()
                .toAddresses(user.getEmail())
                .build();

        Content subjectContent = Content.builder()
                .data(subject)
                .build();

        Content bodyContent = Content.builder()
                .data(bodyText)
                .build();

        Body body = Body.builder()
                .text(bodyContent)
                .build();

        Message message = Message.builder()
                .subject(subjectContent)
                .body(body)
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source("tu-correo-verificado@dominio.com") // debe estar verificado en SES
                .build();

        sesClient.sendEmail(request);
    }

}
