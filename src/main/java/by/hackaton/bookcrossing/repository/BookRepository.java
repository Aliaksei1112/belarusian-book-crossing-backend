package by.hackaton.bookcrossing.repository;

import by.hackaton.bookcrossing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByOwner_Username(String username);
    List<Book> findByReceiver_Username(String username);

    @Modifying
    @Query("UPDATE Book b set b.sendStatus = false Where id =:id")
    void changeSendStatus(Long id);
}
