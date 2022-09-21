package by.hackaton.bookcrossing.repository;

import by.hackaton.bookcrossing.entity.ChatMessage;
import by.hackaton.bookcrossing.entity.enums.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    @Modifying
    @Query("UPDATE ChatMessage ch SET ch.status = :status WHERE ch.senderId = :senderId AND ch.recipientId = :recipientId")
    void updateStatuses(Long senderId, Long recipientId, MessageStatus status);
}
