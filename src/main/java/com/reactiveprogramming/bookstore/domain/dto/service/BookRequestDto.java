package com.reactiveprogramming.bookstore.domain.dto.service;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookRequestDto {

    private String name;
    private String description;
    private String author;
    private String bookType;
    private double price;
    private String isbn;

}