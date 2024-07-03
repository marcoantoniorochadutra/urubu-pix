package com.urubu.service.impl;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.core.exceptions.BusinessException;
import com.urubu.core.utils.PasswordUtils;
import com.urubu.core.utils.ValidationUtils;
import com.urubu.domain.entity.User;
import com.urubu.domain.entity.UserDetails;
import com.urubu.domain.repository.UserRepository;
import com.urubu.service.UserService;

import io.micrometer.common.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
    }

	@Override
	public User registerUser(String nome, String email, String password) {
		validateRegister(nome, email, password);

		User user = new User();
		user.setName(nome);
		user.setEmail(email);
		user.setHashPass(PasswordUtils.encodeMd5(password));

		UserDetails details = new UserDetails(true, new Locale("en"));
		user.setUserDetails(details);
		return userRepository.saveAndFlush(user);
	}

	private void validateRegister(String nome, String email, String password) {
		if (!PasswordUtils.validatePassword(password)) {
			throw new BusinessException(PasswordUtils.PASSWORD_MESSAGE_MEDIUM);
		}

		if(!ValidationUtils.isValidEmail(email)) {
			throw new BusinessException(ReturnMessage.getMessageWithField(CoreReturnMessage.INVALID_ARGUMENT, "[email]"));
		}

		if(StringUtils.isBlank(nome)) {
			throw new BusinessException(ReturnMessage.getMessageWithField(CoreReturnMessage.NOT_NULL_MESSAGE, "[name]"));
		}
	}

}
