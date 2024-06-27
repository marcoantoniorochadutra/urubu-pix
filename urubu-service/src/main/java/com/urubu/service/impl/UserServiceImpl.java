package com.urubu.service.impl;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urubu.core.config.ErrorMessage;
import com.urubu.core.constants.CoreReturnMessages;
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
			String errorMsg = ErrorMessage.getMessageWithField(CoreReturnMessages.INVALID_ARGUMENT);
			throw new BusinessException(String.format(errorMsg, "[email]"));
		}

		if(StringUtils.isBlank(nome)) {
			String errorMsg = ErrorMessage.getMessageWithField(CoreReturnMessages.NOT_NULL_MESSAGE);
			throw new BusinessException(String.format(errorMsg, "[name]"));
		}
	}

}
