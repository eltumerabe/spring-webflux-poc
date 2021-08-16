package com.reactiveprogramming.bookstore.integration.db;

import com.reactiveprogramming.bookstore.domain.conversion.db.BookDocumentToBookResponseDtoConverter;
import com.reactiveprogramming.bookstore.domain.conversion.db.BookRequestDtoToBookDocumentConverter;
import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import com.reactiveprogramming.bookstore.domain.dto.service.BookResponseDto;
import com.reactiveprogramming.bookstore.integration.db.repository.BookDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationDbService {

    private final BookDocumentRepository bookDocumentRepository;
    private final BookRequestDtoToBookDocumentConverter bookRequestDtoToBookDocumentConverter;
    private final BookDocumentToBookResponseDtoConverter bookDocumentToBookResponseDtoConverter;


    public Mono<BookResponseDto> saveBook(final Mono<BookRequestDto> bookRequestDto) {
        return bookRequestDto
                .map(bookRequestDtoToBookDocumentConverter)
                .flatMap(bookDocument -> bookDocumentRepository.save(bookDocument))
                .map(bookDocumentToBookResponseDtoConverter);
    }

    public Mono<BookResponseDto> findByIsbn(final String isbn) {
        return bookDocumentRepository
                .findByIsbn(isbn)
                .map(bookDocumentToBookResponseDtoConverter);
    }

    public Flux<BookResponseDto> findAllBooks() {
        return bookDocumentRepository.findAll()
                .map(bookDocumentToBookResponseDtoConverter);
    }

    public Mono<Void> delete(final String isbn) {
        return bookDocumentRepository
                .deleteByIsbn(isbn)
                .flatMap(bookDocument -> Mono.empty());
    }

    public Mono<BookResponseDto> updateBook(final Mono<BookRequestDto> bookRequestDto) {
        return bookRequestDto
                .map(bookRequestDtoToBookDocumentConverter)
                .flatMap(bookDocumentRepository::save)
                .map(bookDocumentToBookResponseDtoConverter);
    }

}
