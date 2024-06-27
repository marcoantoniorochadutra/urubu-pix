package com.urubu.service;

import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.core.auth.LoginDto;

public interface AccountService {

    TransactionDto viewBalance(LoginDto login);

    TransactionDto updateProfit(UserDto user);

    AccountDto openAccount(AccountRegisterDto register);



}

