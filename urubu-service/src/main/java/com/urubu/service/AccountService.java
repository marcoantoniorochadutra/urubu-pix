package com.urubu.service;

import com.urubu.domain.ref.AvailableBank;
import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;

public interface AccountService {

    TransactionDto viewBalance(UserDto user);

    TransactionDto updateProfit(UserDto user);

    AccountDto openAccount(UserDto userDto, AvailableBank bank);



}

