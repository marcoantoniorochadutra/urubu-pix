package com.urubu.service;

import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;

public interface AccountService {

    TransactionDto viewBalance(UserDto user);

    TransactionDto updateProfit(UserDto user);

    AccountDto openAccount(AccountRegisterDto register);



}

