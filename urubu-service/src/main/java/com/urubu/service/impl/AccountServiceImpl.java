package com.urubu.service.impl;

import com.urubu.core.auth.LoginDto;
import com.urubu.model.AccountDto;
import com.urubu.model.base.MessageDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.core.exceptions.AccountException;
import com.urubu.core.utils.ValidationUtils;
import com.urubu.domain.entity.Account;
import com.urubu.domain.entity.User;
import com.urubu.domain.repository.AccountRepository;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.service.AccountService;
import com.urubu.service.UserService;

import java.util.Objects;

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
    public AccountDto viewBalance(LoginDto login) {
        Account account = accountRepository.findByAccountIdentifier(login.getAccountIdentifier());
        validateAccount(login, account);
        return modelMapper.map(account, AccountDto.class);
    }

    private void validateAccount(LoginDto login, Account account) {
        if (Objects.isNull(account)) {
            throw new AccountException(ReturnMessage.getMessageWithField(CoreReturnMessage.NO_RECORD_FOUND, "[account]"));
        }
        if(!account.getUser().getEmail().equalsIgnoreCase(login.getEmail())) {
            throw new AccountException(ReturnMessage.getMessage(CoreReturnMessage.LOGIN_UNAUTHORIZED_ERROR));
        }

    }


    @Override
    public MessageDto openAccount(AccountRegisterDto register) {

        validateRegister(register);
        User user = userService.registerUser(register.getName(), register.getEmail(), register.getPassword());

        Account newAccount = new Account();
        newAccount.setAccountIdentifier(generateAccountIdentifier());
        newAccount.setBalance(0.0);
        newAccount.setUser(user);

        accountRepository.saveAndFlush(newAccount);

        return new MessageDto(ReturnMessage.getMessage(CoreReturnMessage.ACCOUNT_SUCESS));
    }

    private void validateRegister(AccountRegisterDto register) {
		if (!ValidationUtils.isNationalRegistryValid(register.getNationalRegistry())) {
            throw new AccountException(ReturnMessage.getMessage(CoreReturnMessage.INVALID_ARGUMENT) + " [nationalRegistry]");
		}
    }

    private String generateAccountIdentifier() {
        return "account_" + RandomStringUtils.random(20, true, true);
    }
}
