package com.reactiveprogramming.bookstore.domain.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookDocument {

    @Id
    private String isbn;
    private String name;
    private String description;
    private String author;
    private String bookType;
    private double price;

}
