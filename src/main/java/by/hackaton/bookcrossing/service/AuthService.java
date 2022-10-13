package by.hackaton.bookcrossing.service;

import by.hackaton.bookcrossing.dto.LoginRequest;

public interface AuthService {
    void signIn(LoginRequest login);
    void login(LoginRequest login);
}
