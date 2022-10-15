package by.hackaton.bookcrossing.dto.security;

import by.hackaton.bookcrossing.dto.AccountDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthResponse implements Serializable {

    private Token token;

    private AccountDto account;

    public AuthResponse(Token token, AccountDto account) {
        this.token = token;
        this.account = account;
    }
}
