package com.reactiveprogramming.bookstore.util;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestBodySerializerUtil {

    public <T> Mono<T> bodyToMono(@NonNull final ServerRequest request, @NonNull final Class<T> type) {
        return request.bodyToMono(type);
    }

    public <T> Flux<T> bodyToFlux(@NonNull final ServerRequest request, @NonNull final Class<T> type) {
        return request.bodyToFlux(type);
    }
}