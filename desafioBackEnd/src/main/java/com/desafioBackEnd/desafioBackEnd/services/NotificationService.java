package com.desafioBackEnd.desafioBackEnd.services;

import com.desafioBackEnd.desafioBackEnd.domain.user.User;
import com.desafioBackEnd.desafioBackEnd.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationResquest = new NotificationDTO(email,message);

        /*ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationResquest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Notificação não enviada");
            throw new Exception("Notificação não enviada");
        }

         */
        System.out.println("Transferencia feita");

    }

}
