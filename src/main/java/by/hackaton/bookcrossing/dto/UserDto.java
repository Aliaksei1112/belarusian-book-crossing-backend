package by.hackaton.bookcrossing.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto extends UserShortDto {
    private String email;
    private List<BookDto> books;
}
