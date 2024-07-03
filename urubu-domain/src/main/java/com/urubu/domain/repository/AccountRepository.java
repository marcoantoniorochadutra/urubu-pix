package com.urubu.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urubu.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUserId(Long id);

    Account findByAccountIdentifier(String identifier);


}
