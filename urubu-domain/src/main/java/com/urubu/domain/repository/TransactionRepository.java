package com.urubu.domain.repository;

import com.urubu.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.urubu.domain.entity.Account;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByTransactionIdentifier(String transactionIdentifier);

}
