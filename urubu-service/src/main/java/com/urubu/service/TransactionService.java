package com.urubu.service;

import com.urubu.core.auth.LoginDto;
import com.urubu.model.TransactionDto;

public interface TransactionService {

	TransactionDto deposit(LoginDto login, TransactionDto dto);

	TransactionDto withdraw(LoginDto login, TransactionDto dto);

	TransactionDto transfer(LoginDto login, TransactionDto dto, String accountIdentifier);

	void manageTransaction(String transactionIdentifier);

}
