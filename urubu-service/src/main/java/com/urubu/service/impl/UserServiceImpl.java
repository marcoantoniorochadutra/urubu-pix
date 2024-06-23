package com.urubu.service.impl;

import com.urubu.core.config.ErrorMessage;
import com.urubu.core.constants.CoreReturnMessages;
import com.urubu.core.exceptions.BusinessException;
import com.urubu.core.utils.PasswordUtils;
import com.urubu.core.utils.ValidationUtils;
import com.urubu.domain.entity.User;
import com.urubu.domain.repository.UserRepository;
import com.urubu.model.UserDto;
import com.urubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(String nome, String email, String password) {
		
		validateData(nome, email, password);
		User user = new User();
		user.setNome(nome);
		user.setEmail(email);
		user.setHashSenha(PasswordUtils.encodeMd5(password));

		return userRepository.saveAndFlush(user);
	}

	private void validateData(String nome, String email, String password) {
		if (!PasswordUtils.validatePassword(password)) {
			throw new BusinessException(PasswordUtils.PASSWORD_MESSAGE_MEDIUM);
		}

		if(!ValidationUtils.isValidEmail(email)) {
			throw new BusinessException(ErrorMessage.getMessage(CoreReturnMessages.INVALID_ARGUMENTO) + "[email]");
		}
	}

	@Override
	public UserDto login(UserDto userDto) {
		return null;
	}
}
