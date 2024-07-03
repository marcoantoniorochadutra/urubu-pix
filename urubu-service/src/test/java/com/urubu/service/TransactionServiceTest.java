package com.urubu.service;

import com.urubu.model.TransactionDto;
import com.urubu.model.UserDto;
import com.urubu.model.base.SelectableDto;
import com.urubu.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceTest extends AbstractTestSupport {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void shouldDeposit() {
        TransactionDto dto = new TransactionDto();
        dto.setUser(new UserDto("Mock User", "mockuser@bank.com"));
        dto.setAccount(new SelectableDto("account_vdmhMSHCZhOOj8uR2TMb"));
        dto.setAmount(100.00);
        dto.setPaymentMethod(new SelectableDto("PIX"));

        TransactionDto result = transactionService.deposit(loginDefault(), dto);

    }
}
