package com.desafioBackEnd.desafioBackEnd.controllers;


import com.desafioBackEnd.desafioBackEnd.domain.transaction.Transaction;
import com.desafioBackEnd.desafioBackEnd.dtos.TransactionDTO;
import com.desafioBackEnd.desafioBackEnd.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
