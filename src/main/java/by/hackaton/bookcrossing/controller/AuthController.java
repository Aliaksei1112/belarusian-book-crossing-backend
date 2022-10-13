package by.hackaton.bookcrossing.controller;

import by.hackaton.bookcrossing.dto.LoginRequest;
import by.hackaton.bookcrossing.service.AuthService;
import by.hackaton.bookcrossing.util.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<Void> signIn(@RequestBody LoginRequest login){
        authService.signIn(login);
        return ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest login){
        authService.login(login);
        return ok().build();
    }
}
