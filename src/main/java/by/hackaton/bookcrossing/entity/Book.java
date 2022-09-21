package by.hackaton.bookcrossing.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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
    @JoinColumn(name = "book_id")
    private Account owner;
    @ColumnDefault("true")
    private boolean available;
    private String language;
    @OneToMany(mappedBy = "book")
    private List<BookOrder> bookOrders;

    private LocalDateTime createdDate;

    @PrePersist
    public void create() {
        createdDate = LocalDateTime.now();
    }

}
