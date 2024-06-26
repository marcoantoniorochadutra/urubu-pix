package com.urubu.domain.repository;

import com.urubu.domain.ref.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import com.urubu.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
