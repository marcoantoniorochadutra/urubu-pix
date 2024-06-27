package com.urubu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.urubu.core.constants.SystemConstants;
import com.urubu.domain.entity.User;
import com.urubu.domain.entity.UserDetails;
import com.urubu.model.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.urubu.core.exceptions.AccountException;
import com.urubu.core.exceptions.BusinessException;
import com.urubu.model.AccountDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.support.AbstractTestSupport;

public class UserServiceTest extends AbstractTestSupport {

	@Autowired
	private UserService accountService;

	@Test
	public void shouldCreateUser() {

		User user = accountService.registerUser("Marco Dutra", "email@hotmail.com", "123456&!#Claudio");

		assertEquals("Marco Dutra", user.getName());
		assertEquals("email@hotmail.com", user.getEmail());

		UserDetails details = user.getUserDetails();
		assertTrue(details.getActive());
		assertEquals(SystemConstants.EN_LOCALE, details.getLocale());
	}

	@Test
	public void shouldNotRegisterUserInvalidEmail() {

		BusinessException ex = assertThrows(BusinessException.class,
				() -> accountService.registerUser("Marco Dutra", "email", "123456&!#Claudio"));

		assertEquals("Invalid information [email]", ex.getMessage());
	}

	@Test
	public void shouldNotRegisterUserInvalidNome() {

		BusinessException ex = assertThrows(BusinessException.class,
				() -> accountService.registerUser(null, "email@hotmail.com", "123456&!#Claudio"));

		assertEquals("Field must be filled [name]", ex.getMessage());
	}

	@Test
	public void shouldNotRegisterUserInvalidPass() {

		BusinessException ex = assertThrows(BusinessException.class,
				() -> accountService.registerUser("Marco Dutra", "email@hotmail.com", "123456"));

		assertEquals("The password must contain letters and numbers, between 6 and 20 characters, and may include special characters.", ex.getMessage());
	}

}
