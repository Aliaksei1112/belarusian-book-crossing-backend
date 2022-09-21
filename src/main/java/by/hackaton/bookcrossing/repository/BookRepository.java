package by.hackaton.bookcrossing.repository;

import by.hackaton.bookcrossing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByOwner_Username(String username);
}
