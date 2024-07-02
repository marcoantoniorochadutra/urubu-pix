package com.urubu.web.endpoint;

import com.urubu.core.auth.Authentication;
import com.urubu.model.AccountDto;
import com.urubu.model.TransactionDto;
import com.urubu.model.auth.AccountRegisterDto;
import com.urubu.core.auth.LoginDto;
import com.urubu.model.base.MessageDto;
import com.urubu.service.AccountService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.ws.rs.Path;

@Path("/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @Path("/open-account")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageDto register(AccountRegisterDto register) {
        return accountService.openAccount(register);
    }

    @GET
    @Authentication
    @Path("/view-balance")
    @Produces({MediaType.APPLICATION_JSON})
    public TransactionDto viewBalance(@Context LoginDto login) {
        return accountService.viewBalance(login);
    }

}
