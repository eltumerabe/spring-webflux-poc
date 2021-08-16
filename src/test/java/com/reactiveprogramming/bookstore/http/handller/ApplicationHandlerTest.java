package com.reactiveprogramming.bookstore.http.handller;

import com.reactiveprogramming.bookstore.domain.conversion.ServerRequestToPathVariableConverter;
import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import com.reactiveprogramming.bookstore.domain.dto.service.BookResponseDto;
import com.reactiveprogramming.bookstore.processor.BookStoreProcessor;
import com.reactiveprogramming.bookstore.util.RequestBodySerializerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

/*@RunWith(SpringRunner.class)
//@WebFluxTest(ApplicationHandler.class)
@SpringBootTest
@AutoConfigureWebTestClient*/
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
//@ActiveProfiles("local")
public class ApplicationHandlerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    //@Autowired
    private BookStoreProcessor bookStoreProcessor;
    @MockBean
    //@Autowired
    private ServerRequestToPathVariableConverter serverRequestToPathVariableConverter;
    @MockBean
    //@Autowired
    private RequestBodySerializerUtil requestBodySerializerUtil;

    Mono<BookRequestDto> bookRequestDtoMono;
    Mono<BookResponseDto> bookResponseDtoMono;

    @BeforeEach
    public void setUp() {
        bookRequestDtoMono = Mono.just(
                BookRequestDto.builder()
                        .isbn(null)
                        .name("Data Structure")
                        .description("This book gives you full idea about data structure")
                        .author("Imdad Areef")
                        .bookType("CS")
                        .price(60d)
                        .build()
        );
        bookResponseDtoMono = Mono.just(
                BookResponseDto.builder()
                        .isbn(null)
                        .name("Data Structure")
                        .description("This book gives you full idea about data structure")
                        .author("Imdad Areef")
                        .bookType("CS")
                        .price(60d)
                        .build()
        );
    }

    @Test
    public void createBookTest() {
        when(bookStoreProcessor.createBook(bookRequestDtoMono)).thenReturn(bookResponseDtoMono);
        webTestClient.post().uri("/books")
                .body(Mono.just(bookRequestDtoMono), BookResponseDto.class)
                .exchange()
                .expectStatus().isOk();
    }

}
