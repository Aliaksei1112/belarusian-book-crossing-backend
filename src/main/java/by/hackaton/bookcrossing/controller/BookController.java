package by.hackaton.bookcrossing.controller;

import by.hackaton.bookcrossing.configuration.oauth.CustomOAuth2User;
import by.hackaton.bookcrossing.dto.BookDto;
import by.hackaton.bookcrossing.entity.Account;
import by.hackaton.bookcrossing.entity.Book;
import by.hackaton.bookcrossing.repository.AccountRepository;
import by.hackaton.bookcrossing.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookRepository bookRepository;
    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    public BookController(BookRepository bookRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookRepository.findAll().stream().map(b -> modelMapper.map(b, BookDto.class)).collect(Collectors.toList());
        return ok(books);
    }

    @GetMapping("/my")
    public ResponseEntity<List<BookDto>> getMyBooks(Authentication auth) {
        String email = ((CustomOAuth2User) auth.getPrincipal()).getEmail();
        List<BookDto> books = bookRepository.findByOwner_Username(email).stream().map(b -> modelMapper.map(b, BookDto.class)).collect(Collectors.toList());
        return ok(books);
    }

    @GetMapping("/received")
    public ResponseEntity<List<BookDto>> getReceivedBooks(Authentication auth) {
        String email = ((CustomOAuth2User) auth.getPrincipal()).getEmail();
        List<BookDto> books = bookRepository.findByReceiver_Username(email).stream().map(b -> modelMapper.map(b, BookDto.class)).collect(Collectors.toList());
        return ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return ok(modelMapper.map(book, BookDto.class));
    }

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody @Valid BookDto dto, Authentication auth) {
        Book book = modelMapper.map(dto, Book.class);
        if (auth != null) {
            String email = ((CustomOAuth2User) auth.getPrincipal()).getEmail();
            Account account = accountRepository.findByUsername(email).orElseThrow();
            book.setOwner(account);
        }
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable("id") Long id, @RequestParam BookDto dto) {
        Book book = bookRepository.findById(id).orElseThrow();
        modelMapper.map(book, dto);
        bookRepository.save(book);
        return ok().build();
    }

    @PutMapping("/send/{id}")
    public ResponseEntity<Void> sendBook(@PathVariable("id") Long id, @RequestParam String username) {
        Book book = bookRepository.findById(id).orElseThrow();
        Account account = accountRepository.findByUsername(username).orElseThrow();
        book.setSendStatus(true);
        book.setReceiver(account);
        bookRepository.save(book);
        return ok().build();
    }

    @PutMapping("/receive/{id}")
    public ResponseEntity<Void> receiveBook(@PathVariable("id") Long id) {
        bookRepository.changeSendStatus(id);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return ok().build();
    }
}
