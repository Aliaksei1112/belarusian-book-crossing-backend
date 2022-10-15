package by.hackaton.bookcrossing.dto.security;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Token implements Serializable {
    private String accessToken;

    private String tokenType = TokenProvider.TOKEN_TYPE;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }
}
