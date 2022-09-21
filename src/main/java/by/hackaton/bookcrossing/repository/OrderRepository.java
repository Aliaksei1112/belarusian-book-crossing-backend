package by.hackaton.bookcrossing.repository;

import by.hackaton.bookcrossing.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder, Long> {

    List<BookOrder> findByReceiver_Username(String username);

    @Modifying
    @Query("UPDATE BookOrder o set o.sendStatus = :status Where id =:id")
    void changeSendStatus(Long id, String status);
}
