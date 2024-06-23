package com.urubu.service.impl;

import com.urubu.core.exceptions.AccountException;
import com.urubu.domain.entity.Account;
import com.urubu.domain.entity.User;
import com.urubu.domain.ref.AvailableBank;
import com.urubu.domain.repository.AccountRepository;
import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.service.AccountService;
import com.urubu.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              UserService userService,
                              ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransactionDto viewBalance(UserDto user) {
        return null;
    }

    @Override
    public TransactionDto updateProfit(UserDto user) {
        return null;
    }

    @Override
    public AccountDto openAccount(AccountRegisterDto register) {

        validateRegister(register);
        User user = userService.registerUser(register.getName(), register.getEmail(), register.getPassword());

        Account newAccount = new Account();
        newAccount.setAccountIdentifier(generateAccountIdentifier());
        newAccount.setBalance(0.0);
        newAccount.setBank(AvailableBank.BANK_1);
        newAccount.setUser(user);

        Account openAccount = accountRepository.saveAndFlush(newAccount);

        return modelMapper.map(openAccount, AccountDto.class);
    }

    private void validateRegister(AccountRegisterDto register) {
		if (!isNationalRegistrValid(register.getNationalRegistry())) {
            throw new AccountException(".");
		}
    }

    private boolean isNationalRegistrValid(String nationalRegistry) {
        return true;
    }

    private String generateAccountIdentifier() {
        return "account_" + RandomStringUtils.random(10, true, true);
    }
}
