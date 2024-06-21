package com.urubu.service.impl;

import com.urubu.core.exceptions.BusinessException;
import com.urubu.core.utils.PasswordUtils;
import com.urubu.domain.repository.UserRepository;
import com.urubu.model.UserDto;
import com.urubu.model.auth.UserRegisterDto;
import com.urubu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto registerUser(UserRegisterDto userDto) {
        validateData(userDto);

        return null;
    }

    private void validateData(UserRegisterDto userDto) {
       if (!PasswordUtils.validatePassword(userDto.getPassword())) {
           throw new BusinessException(PasswordUtils.PASSWORD_MESSAGE_MEDIUM);
       }

    }

    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }
}
