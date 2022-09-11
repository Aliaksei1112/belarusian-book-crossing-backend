package by.hackaton.bookcrossing.repository;

import by.hackaton.bookcrossing.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT u FROM Account u WHERE u.email = :username")
    Account getUserByUsername(@Param("username") String username);

    Optional<Account> findByUsername(String username);
}
