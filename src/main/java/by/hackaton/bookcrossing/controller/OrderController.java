package by.hackaton.bookcrossing.controller;

import by.hackaton.bookcrossing.dto.CreateOrderDTO;
import by.hackaton.bookcrossing.dto.OrderDto;
import by.hackaton.bookcrossing.entity.Account;
import by.hackaton.bookcrossing.entity.Book;
import by.hackaton.bookcrossing.entity.BookOrder;
import by.hackaton.bookcrossing.entity.enums.BookStatus;
import by.hackaton.bookcrossing.repository.AccountRepository;
import by.hackaton.bookcrossing.repository.BookRepository;
import by.hackaton.bookcrossing.repository.OrderRepository;
import by.hackaton.bookcrossing.util.AuthUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    public OrderController(OrderRepository orderRepository, BookRepository bookRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getReceivedBooks(Authentication auth) {
        String email = AuthUtils.getEmailFromAuth(auth);
        List<OrderDto> orders = orderRepository.findByReceiver_Username(email).stream().map(o -> modelMapper.map(o, OrderDto.class)).collect(Collectors.toList());
        return ok(orders);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> sendBook(@RequestBody CreateOrderDTO dto) {
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow();
        book.setAvailable(false);
        bookRepository.save(book);
        Account sendTo = accountRepository.findByUsername(dto.getReceiver()).orElseThrow();
        BookOrder bookOrder = BookOrder.builder().book(book).receiver(sendTo).sendType(dto.getSendType()).sendStatus(BookStatus.RESERVED).build();
        orderRepository.save(bookOrder);
        return ok().build();
    }

    @PutMapping("/sent")
    public ResponseEntity<Void> sendBook(@RequestBody Long id) {
        orderRepository.changeSendStatus(id, BookStatus.SENT.name());
        return ok().build();
    }

    @PutMapping("/delivered")
    public ResponseEntity<Void> receiveBook(@RequestBody Long id) {
        orderRepository.changeSendStatus(id, BookStatus.DELIVERED.name());
        return ok().build();
    }
}
