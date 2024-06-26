package com.urubu.service.impl;

import com.urubu.model.TransactionDto;
import com.urubu.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public TransactionDto deposit(TransactionDto dto) {

        return null;
    }

    @Override
    public TransactionDto withdraw(TransactionDto dto) {
        return null;
    }

    @Override
    public TransactionDto invest(TransactionDto dto) {
        return null;
    }

    @Override
    public TransactionDto transfer(TransactionDto dto, String accountIdentifier) {
        return null;
    }
}
