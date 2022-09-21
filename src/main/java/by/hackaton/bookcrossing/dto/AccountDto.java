package by.hackaton.bookcrossing.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountDto extends AccountShortDto {
    private String email;
    private List<BookDto> books;
}
