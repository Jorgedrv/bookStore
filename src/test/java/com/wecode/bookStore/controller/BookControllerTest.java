package com.wecode.bookStore.controller;

import static org.mockito.Mockito.when;

import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.mock.BookMock;
import com.wecode.bookStore.mock.UserMock;
import com.wecode.bookStore.service.BookService;
import com.wecode.bookStore.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    public static final String TEST_TITLE = "test title";

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testControllerShouldReturnBookList() {
        when(bookService.getBooks()).thenReturn(BookMock.getBookDtoList());
        ResponseEntity<List<BookDto>> books = bookController.getBooks();
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.getBody().size()).isEqualTo(1);
    }

    @Test
    void testControllerShouldReturnBooksByTitle() {
        when(bookService.getBooksByTitle(TEST_TITLE)).thenReturn(BookMock.getBookDtoList());
        ResponseEntity<List<BookDto>> books = bookController.getBooksByTitle(TEST_TITLE);
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.getBody().size()).isEqualTo(1);
    }
}
