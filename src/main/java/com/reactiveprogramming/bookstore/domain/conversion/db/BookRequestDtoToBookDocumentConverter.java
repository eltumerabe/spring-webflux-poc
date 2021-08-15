package com.reactiveprogramming.bookstore.domain.conversion.db;

import com.reactiveprogramming.bookstore.domain.db.BookDocument;
import com.reactiveprogramming.bookstore.domain.dto.service.BookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookRequestDtoToBookDocumentConverter implements Function<BookRequestDto, BookDocument> {

    private final ModelMapper modelMapper;

    @Override
    public BookDocument apply(final BookRequestDto bookRequestDto) {
        return modelMapper.map(bookRequestDto, BookDocument.class);
    }
}
