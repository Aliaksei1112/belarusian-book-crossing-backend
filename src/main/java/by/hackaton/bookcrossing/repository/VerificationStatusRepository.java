package by.hackaton.bookcrossing.repository;

import by.hackaton.bookcrossing.entity.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationStatusRepository extends JpaRepository<VerificationStatus, Long> {

    Optional<VerificationStatus> findByEmailAndCode(String email, String code);
}
