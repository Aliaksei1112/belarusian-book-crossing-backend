package by.hackaton.bookcrossing.service.impl;

import by.hackaton.bookcrossing.dto.LoginRequest;
import by.hackaton.bookcrossing.entity.Account;
import by.hackaton.bookcrossing.repository.AccountRepository;
import by.hackaton.bookcrossing.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AccountRepository accountRepository;

    public AuthServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void signIn(LoginRequest login) {

    }

    @Override
    public void login(LoginRequest login) {

    }
}
