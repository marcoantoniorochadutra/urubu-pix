package com.urubu.service.impl;

import com.urubu.core.auth.LoginDto;
import com.urubu.model.TransactionDto;
import com.urubu.service.TransactionService;
import jakarta.ws.rs.core.Context;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public TransactionDto deposit(@Context LoginDto login, TransactionDto dto) {
		return null;
	}

	@Override
	public TransactionDto withdraw(@Context LoginDto login, TransactionDto dto) {
		return null;
	}

	@Override
	public TransactionDto invest(@Context LoginDto login, TransactionDto dto) {
		return null;
	}

	@Override
	public TransactionDto transfer(@Context LoginDto login, TransactionDto dto, String accountIdentifier) {
		return null;
	}
}
