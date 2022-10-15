package by.hackaton.bookcrossing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    public String email;
    public String password;
}
