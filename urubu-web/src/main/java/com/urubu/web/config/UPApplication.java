package com.urubu.web.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UPApplication {
    // TODO Melhorar Mensagens de erro do banco de dados.
    // TODO Melhorar testes de conta.
    public static void main(String[] args) {
        SpringApplication.run(UPApplication.class, args);
    }
}