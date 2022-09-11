package by.hackaton.bookcrossing.controller;

import by.hackaton.bookcrossing.dto.UserDto;
import by.hackaton.bookcrossing.dto.UserShortDto;
import by.hackaton.bookcrossing.entity.Account;
import by.hackaton.bookcrossing.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private AccountRepository repository;
    private ModelMapper modelMapper;

    public ProfileController(AccountRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(Authentication auth) {
        Account user = repository.findByUsername(auth.getName()).orElseThrow();
        return ok(modelMapper.map(user, UserDto.class));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto, Authentication auth) {
        Account user = repository.findByUsername(auth.getName()).orElseThrow();
        modelMapper.map(user, dto);
        repository.save(user);
        return ok(modelMapper.map(user, UserDto.class));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserShortDto> getUserByUsername(@PathVariable("username") String username) {
        Account user = repository.findByUsername(username).orElseThrow();
        return ok(modelMapper.map(user, UserShortDto.class));
    }
}
