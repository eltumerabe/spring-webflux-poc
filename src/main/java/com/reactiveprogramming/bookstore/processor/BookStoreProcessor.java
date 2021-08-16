package com.reactiveprogramming.bookstore.processor;

import com.reactiveprogramming.bookstore.config.Discount;
import com.reactiveprogramming.bookstore.config.DiscountConfig;
import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import com.reactiveprogramming.bookstore.domain.dto.service.BookResponseDto;
import com.reactiveprogramming.bookstore.domain.dto.service.PayableAmount;
import com.reactiveprogramming.bookstore.integration.db.ApplicationDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookStoreProcessor {

    private final ApplicationDbService applicationDbService;
    private final DiscountConfig discountConfig;

    public Mono<BookResponseDto> createBook(final Mono<BookRequestDto> bookRequestDtoMono) {
        return applicationDbService.saveBook(bookRequestDtoMono);
    }

    public Mono<BookResponseDto> updateBook(final Mono<BookRequestDto> bookRequestDtoMono) {
        return applicationDbService.updateBook(bookRequestDtoMono);
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

    public Mono<PayableAmount> checkout(final Flux<BookRequestDto> bookRequestDtoFlux, final String discount) {
        return bookRequestDtoFlux
                .map(this::calculatePrice)
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .map(aDouble -> PayableAmount.builder().amountToPay(aDouble).build());
    }

    private Double calculatePrice(BookRequestDto bookRequestDto) {
        Map<String, Discount> promos = discountConfig.getPromos();
        return Optional.ofNullable(promos.get(bookRequestDto.getBookType()))
                .map(discount -> {
                    double discountPrice = bookRequestDto.getPrice();
                    if (!discount.getExpireDate().isBefore(LocalDate.now())) {
                        if (discount.getDiscount().intValue() > 0) {
                            discountPrice = ((100 - discount.getDiscount()) * bookRequestDto.getPrice()) / 100;
                        }
                    }
                    return discountPrice;
                }).get();
    }
}