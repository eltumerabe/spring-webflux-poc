package com.reactiveprogramming.bookstore.http.handlers;

import com.reactiveprogramming.bookstore.domain.conversion.ServerRequestToPathVariableConverter;
import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import com.reactiveprogramming.bookstore.domain.dto.service.BookResponseDto;
import com.reactiveprogramming.bookstore.domain.dto.service.PayableAmount;
import com.reactiveprogramming.bookstore.processor.BookStoreProcessor;
import com.reactiveprogramming.bookstore.util.RequestBodySerializerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApplicationHandler {

    private final BookStoreProcessor bookStoreProcessor;
    private final ServerRequestToPathVariableConverter serverRequestToPathVariableConverter;
    private final RequestBodySerializerUtil requestBodySerializerUtil;

    public Mono<ServerResponse> createBook(final ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(bookStoreProcessor.createBook(requestBodySerializerUtil.bodyToMono(serverRequest, BookRequestDto.class)), BookResponseDto.class);
    }

    public Mono<ServerResponse> updateBook(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookStoreProcessor.updateBook(requestBodySerializerUtil.bodyToMono(serverRequest, BookRequestDto.class)), BookResponseDto.class);
    }

    public Mono<ServerResponse> deleteBook(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookStoreProcessor.deleteBook(serverRequestToPathVariableConverter.apply("isbn", serverRequest)), BookResponseDto.class);
    }

    public Mono<ServerResponse> getBook(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookStoreProcessor.getBook(serverRequestToPathVariableConverter.apply("isbn", serverRequest)), BookResponseDto.class);
    }

    public Mono<ServerResponse> getBooks(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookStoreProcessor.getBooks(), BookResponseDto.class);
    }

    public Mono<ServerResponse> checkout(final ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookStoreProcessor.checkout(requestBodySerializerUtil.bodyToFlux(serverRequest, BookRequestDto.class), null), PayableAmount.class);
    }
}