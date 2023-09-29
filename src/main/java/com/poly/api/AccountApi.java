package com.poly.api;

import com.poly.dto.AccountDTO;
import com.poly.exception.EntityNotFoundException;
import com.poly.model.Account;
import com.poly.payload.response.APIResponse;
import com.poly.repository.AccountDAO;
import com.poly.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/accounts")
public class AccountApi {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody AccountDTO dto) {
        Account acc = accountDAO.findByEmail(dto.getEmail()).orElseThrow(() -> {
            throw new EntityNotFoundException("Email is not registered!");
        });
        if (passwordEncoder.matches(dto.getPassword(), acc.getPassword())) {
            return ResponseEntity.ok(new APIResponse("Password incorrect", false, null));
        }
        String token = tokenProvider.generateToken(acc);
        return ResponseEntity.ok(APIResponse.builder().success(true).data(acc).message(token).build());
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountDTO dto) {
        Account acc = accountDAO.findByEmail(dto.getEmail()).orElse(null);
        if(acc !=null){
            return ResponseEntity.ok(new APIResponse("Email is registered before", false));
        }
        Account account = new Account();
        account.setEmail(dto.getEmail());
        String encryptPassword = passwordEncoder.encode(dto.getPassword());
        account.setPassword(encryptPassword);
        accountDAO.save(account);
        return ResponseEntity.ok(new APIResponse("Success"));
    }
}
