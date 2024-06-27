package com.urubu.service;

import com.urubu.core.auth.LoginOrigin;
import com.urubu.core.auth.LoginWeb;
import com.urubu.model.auth.AccountAcessDto;

public interface AuthenticationService {

    LoginWeb login(AccountAcessDto userDto, LoginOrigin origin);
}
