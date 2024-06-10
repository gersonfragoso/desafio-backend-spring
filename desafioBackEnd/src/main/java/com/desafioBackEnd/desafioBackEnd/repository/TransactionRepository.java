package com.desafioBackEnd.desafioBackEnd.repository;

import com.desafioBackEnd.desafioBackEnd.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
