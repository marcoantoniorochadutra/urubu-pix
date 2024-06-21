package com.urubu.domain.repository;

import com.urubu.domain.ref.AvailableBank;
import org.springframework.data.jpa.repository.JpaRepository;

import com.urubu.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean existsByUserIdAndBank(Long userId, AvailableBank bank);
}
