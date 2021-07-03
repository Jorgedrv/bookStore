package com.wecode.bookStore.mock;


import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.domain.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookMock {

    public static List<Book> getBookList() {
        List<Book> list = new ArrayList<>();
        list.add(getBook());
        return list;
    }

    public static Book getBook() {
        return Book.builder()
                .title("Test title")
                .id(UUID.fromString("93071342-d9f3-11eb-b8bc-0242ac130005"))
                .description("Test description")
                .releaseYear(2021)
                .build();
    }

    public static List<BookDto> getBookDtoList() {
        List<BookDto> list = new ArrayList<>();
        list.add(getBookDto());
        return list;
    }

    public static BookDto getBookDto() {
        return BookDto.builder()
                .title("Test title")
                .id(UUID.fromString("93071342-d9f3-11eb-b8bc-0242ac130005"))
                .description("Test description")
                .releaseYear(2021)
                .build();
    }
}

