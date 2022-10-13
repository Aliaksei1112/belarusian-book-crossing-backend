package by.hackaton.bookcrossing.service.impl;

import by.hackaton.bookcrossing.dto.AccountDto;
import by.hackaton.bookcrossing.dto.AccountShortDto;
import by.hackaton.bookcrossing.entity.Account;
import by.hackaton.bookcrossing.repository.AccountRepository;
import by.hackaton.bookcrossing.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDto getUser(String username) {
        Account user = accountRepository.findByUsername(username).orElseThrow();
        return modelMapper.map(user, AccountDto.class);
    }

    @Override
    public AccountShortDto getUserByUsername(String username) {
        Account user = accountRepository.findByUsername(username).orElseThrow();
        return modelMapper.map(user, AccountShortDto.class);
    }

    @Override
    public AccountDto updateUser(String username, AccountDto dto) {
        Account user = accountRepository.findByUsername(username).orElseThrow();
        modelMapper.map(user, dto);
        accountRepository.save(user);
        return modelMapper.map(user, AccountDto.class);
    }

    @Override
    public void resetPassword(String newPassword, String email) {
        Account account = accountRepository.findByEmail(email).orElseThrow();
        account.setPassword(newPassword);
        accountRepository.save(account);
    }
}
