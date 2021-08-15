package com.reactiveprogramming.bookstore.integration.db.repository;

import com.reactiveprogramming.bookstore.domain.db.BookDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@DataMongoTest
public class BookDocumentRepositoryTest {
    @Autowired
    BookDocumentRepository bookDocumentRepository;

    List<BookDocument> list = Arrays.asList(
            BookDocument.builder()
                    .isbn(null)
                    .name("Concept of programming language")
                    .description("This book gives you full idea about the concept of programming language")
                    .author("Mohammed yusuf")
                    .bookType("CS")
                    .price(77d)
                    .build()
            ,
            BookDocument.builder()
                    .isbn(null)
                    .name("Data Structure")
                    .description("This book gives you full idea about data structure")
                    .author("Imdad Areef")
                    .bookType("CS")
                    .price(60d)
                    .build(),
            BookDocument.builder()
                    .isbn(null)
                    .name("Restfull design")
                    .description("This book gives you full idea about how to design Restfull webservice")
                    .author("Praveen")
                    .bookType("CS")
                    .price(50d)
                    .build()
    );

    @BeforeEach
    public void setUp() {
        bookDocumentRepository.deleteAll()
                .thenMany(Flux.fromIterable(list))
                .flatMap(bookDocumentRepository::save)
                .doOnNext(bookDocument -> {
                    System.out.println("Inserted Book is: " + bookDocument);
                })
                .blockLast();
    }

    @Test
    public void getAllBookDocuments() {
        StepVerifier.create(bookDocumentRepository.findAll())
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }
}
