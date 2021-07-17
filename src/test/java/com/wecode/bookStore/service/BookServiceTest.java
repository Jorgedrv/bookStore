package com.wecode.bookStore.service;

import static org.mockito.Mockito.when;

import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.mock.BookMock;
import com.wecode.bookStore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void testFindAllBookShouldReturnList() {
        when(bookRepository.findAll()).thenReturn(BookMock.getBookList());
        when(modelMapper.map(BookMock.getBook(), BookDto.class)).thenReturn(BookMock.getBookDto());
        Assertions.assertThat(1).isEqualTo(bookService.getBooks().size());
        Assertions.assertThat(bookService.getBooks().get(0))
                .hasFieldOrPropertyWithValue("title", "Test title")
                .hasFieldOrPropertyWithValue("description", "Test description")
                .hasFieldOrPropertyWithValue("releaseYear", 2021);
    }

    @Test
    void testFindAllBookByTitleIgnoreCaseShouldReturnList() {
        when(bookRepository.findBooksByTitleIgnoreCase("tEst title")).thenReturn(BookMock.getBookList());
        when(modelMapper.map(BookMock.getBook(), BookDto.class)).thenReturn(BookMock.getBookDto());
        List<BookDto> booksByTitle = bookService.getBooksByTitle("tEst title");
        Assertions.assertThat(1).isEqualTo(booksByTitle.size());
        Assertions.assertThat(booksByTitle.get(0))
                .hasFieldOrPropertyWithValue("title", "Test title")
                .hasFieldOrPropertyWithValue("description", "Test description")
                .hasFieldOrPropertyWithValue("releaseYear", 2021);
    }
}
