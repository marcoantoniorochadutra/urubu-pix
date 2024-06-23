package com.urubu.service;

import com.urubu.domain.entity.User;
import com.urubu.model.UserDto;

public interface UserService {

    User registerUser(String nome, String email, String password);

    UserDto login(UserDto userDto);
}
