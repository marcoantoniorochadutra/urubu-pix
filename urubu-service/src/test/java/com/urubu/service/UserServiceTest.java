package com.urubu.service;

import com.urubu.core.utils.PasswordUtils;
import com.urubu.model.AccountDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.support.AbstractTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class UserServiceTest extends AbstractTestSupport {

	@Autowired
	private AccountService accountService;

	@Test
	public void shouldOpenAccount() {

		AccountRegisterDto dto = new AccountRegisterDto();
		dto.setName("Marco Dutra");
		dto.setEmail("email@hotmail.com");
		dto.setNationalRegistry("12605716961");
		dto.setPassword("123456&!#Claudio");

		AccountDto createdAccount = accountService.openAccount(dto);
		System.err.println(createdAccount);


	}
}
