package com.poly.security;

import com.poly.model.Account;
import com.poly.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    AccountDAO accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("Can not find this account");
        });
        return UserPrincipal.create(account);
    }

    public UserDetails loadUserById(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Can not find this account");
        });
        return UserPrincipal.create(account);
    }
}
