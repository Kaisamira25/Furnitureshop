package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Account;

import java.util.Optional;

public interface AccountDAO extends JpaRepository<Account, Integer> {
    Account findByPassword(String password);

    Optional<Account> findByEmail(String email);
}
