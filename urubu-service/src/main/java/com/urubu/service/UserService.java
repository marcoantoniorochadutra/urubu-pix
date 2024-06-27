package com.urubu.service;

import com.urubu.core.auth.LoginDto;
import com.urubu.core.auth.LoginOrigin;
import com.urubu.core.auth.LoginWeb;
import com.urubu.domain.entity.User;
import com.urubu.model.UserDto;
import com.urubu.model.auth.AccountAcessDto;

public interface UserService {

    User registerUser(String nome, String email, String password);

}
