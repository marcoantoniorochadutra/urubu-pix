package com.urubu.web.endpoint;

import com.urubu.model.AccountDto;
import com.urubu.service.AccountService;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import org.springframework.beans.factory.annotation.Autowired;

import com.urubu.model.UserDto;
import com.urubu.service.UserService;

import jakarta.ws.rs.Path;

@Path("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Path("/open-account/{bank}")
    public AccountDto register(@Context UserDto userDto,
                               @PathParam("bank") String bank) {
        return accountService.openAccount(userDto, null);
    }

}
