package com.urubu.service;

import com.urubu.model.UserDto;
import com.urubu.model.auth.UserRegisterDto;

public interface UserService {

    UserDto registerUser(UserRegisterDto userDto);

    UserDto login(UserDto userDto);
}
