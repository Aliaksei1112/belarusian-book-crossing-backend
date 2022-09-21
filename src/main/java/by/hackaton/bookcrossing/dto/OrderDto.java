package by.hackaton.bookcrossing.dto;

import by.hackaton.bookcrossing.entity.enums.BookStatus;
import by.hackaton.bookcrossing.entity.enums.SendType;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private BookDto book;
    private AccountShortDto receiver;
    private SendType sendType;
    private BookStatus sendStatus;
}
