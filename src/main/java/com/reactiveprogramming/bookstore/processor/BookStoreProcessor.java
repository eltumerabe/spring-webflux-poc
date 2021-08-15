package com.reactiveprogramming.bookstore.processor;

import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import com.reactiveprogramming.bookstore.domain.dto.service.BookResponseDto;
import com.reactiveprogramming.bookstore.integration.db.ApplicationDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookStoreProcessor {

    private final ApplicationDbService applicationDbService;

    public Mono<BookResponseDto> createBook(final Mono<BookRequestDto> bookRequestDtoMono) {
        return applicationDbService.saveBook(bookRequestDtoMono);
    }

    public Mono<BookResponseDto> updateBook(final Mono<BookRequestDto> bookRequestDtoMono) {
        return null;
    }

    public Mono<Void> deleteBook(final String isbn) {
        return applicationDbService.delete(isbn);
    }

    public Mono<BookResponseDto> getBook(final String isbn) {
        return applicationDbService.findByIsbn(isbn);
    }

    public Flux<BookResponseDto> getBooks() {
        return applicationDbService.findAllBooks();
    }
}
