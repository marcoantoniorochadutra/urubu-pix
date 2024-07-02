package com.urubu.service;

import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.core.auth.LoginDto;
import com.urubu.model.base.MessageDto;

public interface AccountService {

    TransactionDto viewBalance(LoginDto login);

    TransactionDto updateProfit(UserDto user);

    MessageDto openAccount(AccountRegisterDto register);



}

