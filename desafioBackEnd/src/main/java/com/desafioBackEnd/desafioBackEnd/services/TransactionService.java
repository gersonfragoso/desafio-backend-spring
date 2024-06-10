package com.desafioBackEnd.desafioBackEnd.services;

import com.desafioBackEnd.desafioBackEnd.domain.transaction.Transaction;
import com.desafioBackEnd.desafioBackEnd.domain.user.User;
import com.desafioBackEnd.desafioBackEnd.dtos.TransactionDTO;
import com.desafioBackEnd.desafioBackEnd.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean authorize = this.authorizeTransaction(sender, transaction.value());
        if (!authorize) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setAmount(transaction.value());
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setAmount(sender.getAmount().subtract(transaction.value()));
        receiver.setAmount(receiver.getAmount().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transferência feita com sucesso");
        this.notificationService.sendNotification(receiver, "Transferência recebida");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizeResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if (authorizeResponse.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = authorizeResponse.getBody();
            if (responseBody != null && responseBody.containsKey("data")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                Boolean authorization = (Boolean) data.get("authorization");
                return authorization != null && authorization;
            }
        }
        return false;
    }
}
