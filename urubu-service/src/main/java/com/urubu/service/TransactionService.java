package com.urubu.service;

import com.urubu.model.TransactionDto;

public interface TransactionService {

    TransactionDto deposit(TransactionDto dto);

    TransactionDto withdraw(TransactionDto dto);

    TransactionDto invest(TransactionDto dto);

    TransactionDto transfer(TransactionDto dto, String accountIdentifier);



}
