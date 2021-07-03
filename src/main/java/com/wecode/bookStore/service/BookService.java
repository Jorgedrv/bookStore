package com.wecode.bookStore.service;

import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.domain.entity.Book;
import com.wecode.bookStore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    public List<BookDto> getBooks() {
        Iterable<Book> all = bookRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .map(getBookBookDtoFunction()).collect(Collectors.toList());
    }

    private Function<Book, BookDto> getBookBookDtoFunction() {
        return book -> modelMapper.map(book, BookDto.class);
    }
}
