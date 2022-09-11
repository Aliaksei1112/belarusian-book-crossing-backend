package by.hackaton.bookcrossing.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private int year;
    private String city;
    private String country;
    private String ISBN;
    private String contacts;
    private String additional;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @ManyToOne
    private Account owner;
    @ManyToOne
    private Account receiver;
    private boolean sendStatus;

    private LocalDateTime createdDate;

    @PrePersist
    public void create() {
        createdDate = LocalDateTime.now();
    }

}
