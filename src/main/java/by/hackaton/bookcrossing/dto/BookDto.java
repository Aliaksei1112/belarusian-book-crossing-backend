package by.hackaton.bookcrossing.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDto {
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
    private String owner;
    private boolean sendStatus;
}
