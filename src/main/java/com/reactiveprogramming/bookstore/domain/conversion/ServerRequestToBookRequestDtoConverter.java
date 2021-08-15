package com.reactiveprogramming.bookstore.domain.conversion;

import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import com.reactiveprogramming.bookstore.util.RequestBodySerializerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ServerRequestToBookRequestDtoConverter implements Function<ServerRequest, Mono<BookRequestDto>> {
    private final RequestBodySerializerUtil requestBodySerializerUtil;

    @Override
    public Mono<BookRequestDto> apply(ServerRequest serverRequest) {
        return requestBodySerializerUtil.readRequestBody(serverRequest, BookRequestDto.class);
    }
}
