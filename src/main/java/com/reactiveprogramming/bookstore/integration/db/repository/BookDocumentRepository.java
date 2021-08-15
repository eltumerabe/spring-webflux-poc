package com.reactiveprogramming.bookstore.integration.db.repository;

import com.reactiveprogramming.bookstore.domain.db.BookDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BookDocumentRepository extends ReactiveMongoRepository<BookDocument, String> {
    Mono<BookDocument> findByIsbn(final String isbn);

    Mono<Void> deleteByIsbn(final String isbn);
}
