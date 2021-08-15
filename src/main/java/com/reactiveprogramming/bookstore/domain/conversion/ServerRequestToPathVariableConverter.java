package com.reactiveprogramming.bookstore.domain.conversion;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.function.BiFunction;

@Component
public class ServerRequestToPathVariableConverter implements BiFunction<String, ServerRequest, String> {
    @Override
    public String apply(String pathVariableName, ServerRequest serverRequest) {
        return serverRequest.pathVariable(pathVariableName);
    }

}
