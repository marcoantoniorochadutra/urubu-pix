package com.urubu.service.impl;

import com.urubu.core.auth.JwtUtils;
import com.urubu.core.auth.LoginDto;
import com.urubu.core.auth.LoginOrigin;
import com.urubu.core.auth.LoginWeb;
import com.urubu.core.config.ReturnMessage;
import com.urubu.core.constants.CoreReturnMessage;
import com.urubu.core.exceptions.LoginException;
import com.urubu.core.exceptions.ref.LoginError;
import com.urubu.core.utils.PasswordUtils;
import com.urubu.domain.entity.User;
import com.urubu.domain.repository.UserRepository;
import com.urubu.model.auth.AccountAcessDto;
import com.urubu.service.AuthenticationService;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginWeb login(AccountAcessDto acessDto, LoginOrigin origin) {
        User user = getValidUserFromAcess(acessDto);

        LoginWeb login = new LoginWeb();
        LoginDto loginDto = buildLoginDto(user);
        login.setLogin(loginDto);

        String accessToken = JwtUtils.generate(loginDto, "WEB-ABB#" + origin.getDevice(), origin.getIp());
        String refreshToken = JwtUtils.generateRefreshToken();

        login.setRefreshToken(refreshToken);
        login.setAccessToken(accessToken);
        user.getUserDetails().setRefreshToken(refreshToken);
        userRepository.saveAndFlush(user);

        log.info("User Logged In | {} | {} | {} | {} ", user.getId(), user.getEmail(), login.getRefreshToken(), origin.getHost());
        return login;
    }

    private LoginDto buildLoginDto(User user) {
        LoginDto loginDto = new LoginDto();
        loginDto.setName(user.getName());
        loginDto.setEmail(user.getEmail());
        loginDto.setLocale(user.getUserDetails().getLocale().toString());
        return loginDto;
    }


    private User getValidUserFromAcess(AccountAcessDto access) {
        User user = userRepository.findByEmail(access.getLogin());
        validateUsuario(user);
        validateSenha(user.getHashPass(), access.getPass());
        return user;
    }

    private void validateUsuario(User user) {
        if (Objects.isNull(user)) {
            throw new LoginException(ReturnMessage.getMessage(CoreReturnMessage.LOGIN_CREDENTIALS_ERROR), LoginError.CREDENTIALS);
        }

        if (BooleanUtils.isFalse(user.getUserDetails().getActive())) {
            throw new LoginException(ReturnMessage.getMessage(CoreReturnMessage.LOGIN_BLOCKED_ACCOUNT), LoginError.BLOCKED);
        }

    }

    private void validateSenha(String senhaSalva, String senhaAcesso) {
        String md5Encode = PasswordUtils.encodeMd5(senhaAcesso);
        if(!senhaSalva.matches(md5Encode)) {
            throw new LoginException(ReturnMessage.getMessage(CoreReturnMessage.LOGIN_CREDENTIALS_ERROR), LoginError.CREDENTIALS);
        }
    }
}
