package com.urubu.service.impl;

import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.core.exceptions.AccountException;
import com.urubu.domain.entity.Account;
import com.urubu.domain.entity.User;
import com.urubu.domain.ref.AvailableBank;
import com.urubu.domain.repository.AccountRepository;
import com.urubu.domain.repository.UserRepository;
import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
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
    public AccountDto openAccount(UserDto userDto, AvailableBank bank) {

        User user = userRepository.getReferenceById(userDto.getId());

        if(accountRepository.existsByUserIdAndBank(userDto.getId(), null)) {
            throw new AccountException(CoreReturnMessage.EXISTING_ACCOUNT);
        }

        Account newAccount = new Account();
        newAccount.setAccountIdentifier(generateAccountIdentifier());
        newAccount.setBalance(0.0);
        newAccount.setBank(null);
        newAccount.setUser(user);

        return null;
    }

    private String generateAccountIdentifier() {
        return "account_" + RandomStringUtils.random(10, true, true);
    }
}
