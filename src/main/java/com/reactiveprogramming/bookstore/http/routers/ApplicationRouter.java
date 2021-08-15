package com.reactiveprogramming.bookstore.http.routers;

import com.reactiveprogramming.bookstore.http.handlers.ApplicationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ApplicationRouter {

    @Bean
    public RouterFunction<ServerResponse> route(final ApplicationHandler applicationHandler) {
        return RouterFunctions
                .route(POST("/books").and(accept(MediaType.APPLICATION_JSON)), applicationHandler::createBook)
                .andRoute(PUT("/books").and(accept(MediaType.APPLICATION_JSON)), applicationHandler::updateBook)
                .andRoute(DELETE("/books/{isbn}").and(accept(MediaType.APPLICATION_JSON)), applicationHandler::deleteBook)
                .andRoute(GET("/books/{isbn}").and(accept(MediaType.APPLICATION_JSON)), applicationHandler::getBook)
                .andRoute(GET("/books").and(accept(MediaType.APPLICATION_JSON)), applicationHandler::getBooks);
    }

}