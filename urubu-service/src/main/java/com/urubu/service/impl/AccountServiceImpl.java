package com.urubu.service.impl;

import com.urubu.core.auth.LoginDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urubu.core.config.ErrorMessage;
import com.urubu.core.constants.CoreReturnMessages;
import com.urubu.core.exceptions.AccountException;
import com.urubu.core.utils.ValidationUtils;
import com.urubu.domain.entity.Account;
import com.urubu.domain.entity.User;
import com.urubu.domain.repository.AccountRepository;
import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.service.AccountService;
import com.urubu.service.UserService;

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
    public TransactionDto viewBalance(LoginDto login) {
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
        newAccount.setUser(user);

        Account openAccount = accountRepository.saveAndFlush(newAccount);

        return modelMapper.map(openAccount, AccountDto.class);
    }

    private void validateRegister(AccountRegisterDto register) {
		if (!ValidationUtils.isNationalRegistryValid(register.getNationalRegistry())) {
            throw new AccountException(ErrorMessage.getMessage(CoreReturnMessages.INVALID_ARGUMENT) + " [nationalRegistry]");
		}
    }

    private String generateAccountIdentifier() {
        return "account_" + RandomStringUtils.random(20, true, true);
    }
}
