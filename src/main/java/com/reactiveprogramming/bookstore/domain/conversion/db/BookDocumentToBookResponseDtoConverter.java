package com.reactiveprogramming.bookstore.domain.conversion.db;

import com.reactiveprogramming.bookstore.domain.db.BookDocument;
import com.reactiveprogramming.bookstore.domain.dto.service.BookResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookDocumentToBookResponseDtoConverter implements Function<BookDocument, BookResponseDto> {
    private final ModelMapper modelMapper;

    @Override
    public BookResponseDto apply(BookDocument bookDocument) {
        return modelMapper.map(bookDocument, BookResponseDto.class);
    }
}
