package com.urubu.service;

import com.urubu.core.exceptions.AccountException;
import com.urubu.core.exceptions.BusinessException;
import com.urubu.model.AccountDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.model.base.MessageDto;
import com.urubu.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceTest extends AbstractTestSupport {

	@Autowired
	private AccountService accountService;

	@Test
	public void shouldOpenAccount() {

		AccountRegisterDto dto = new AccountRegisterDto();
		dto.setName("Marco Dutra");
		dto.setEmail("email@hotmail.com");
		dto.setNationalRegistry("39999807020");
		dto.setPassword("123456&!#Claudio");

		MessageDto createdAccount = accountService.openAccount(dto);

		assertEquals("Your account has been successfully created", createdAccount.getMessage());
	}

	@Test
	public void shouldNotOpenAccountInvalidRegistry() {

		AccountRegisterDto dto = new AccountRegisterDto();
		dto.setName("Marco Dutra");
		dto.setEmail("email@hotmail.com");
		dto.setNationalRegistry("12605716900");
		dto.setPassword("123456&!#Claudio");

		AccountException ex = assertThrows(AccountException.class, () -> accountService.openAccount(dto));

		assertEquals("Invalid information [nationalRegistry]", ex.getMessage());
	}

	@Test
	public void shouldNotOpenAccountInvalidPass() {

		AccountRegisterDto dto = new AccountRegisterDto();
		dto.setName("Marco Dutra");
		dto.setEmail("email@hotmail.com");
		dto.setNationalRegistry("39999807020");
		dto.setPassword("123456");

		BusinessException ex = assertThrows(BusinessException.class, () -> accountService.openAccount(dto));

		assertEquals("The password must contain letters and numbers, between 6 and 20 characters, and may include special characters.", ex.getMessage());
	}

	@Test
	public void shouldViewBalance() {
		AccountDto balance = accountService.viewBalance(loginDefault());
		assertNotNull(balance);
		assertEquals(0.0, balance.getBalance());
	}
}
