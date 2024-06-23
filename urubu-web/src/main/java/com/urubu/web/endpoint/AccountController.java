package com.urubu.web.endpoint;

import com.urubu.model.AccountDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.service.AccountService;
import jakarta.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.ws.rs.Path;

@Path("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @Path("/open-account/{bank}")
    public AccountDto register(AccountRegisterDto register) {
        return accountService.openAccount(register);
    }

}
