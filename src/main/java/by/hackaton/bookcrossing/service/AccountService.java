package by.hackaton.bookcrossing.service;

import by.hackaton.bookcrossing.dto.AccountDto;
import by.hackaton.bookcrossing.dto.AccountShortDto;

public interface AccountService {
    AccountDto getUser(String username);
    AccountShortDto getUserByUsername(String username);
    AccountDto updateUser(String username, AccountDto dto);
    void resetPassword(String newPassword, String email);
}
