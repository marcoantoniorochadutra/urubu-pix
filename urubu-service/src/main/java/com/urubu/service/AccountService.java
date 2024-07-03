package com.urubu.service;

import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.core.auth.LoginDto;
import com.urubu.model.base.MessageDto;

public interface AccountService {

    AccountDto viewBalance(LoginDto login);

    MessageDto openAccount(AccountRegisterDto register);



}

