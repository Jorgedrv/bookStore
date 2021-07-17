package com.wecode.bookStore.repository;

import com.wecode.bookStore.domain.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RepositoryBookTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Sql(scripts = {"classpath:insertInitialBookRecord.sql"})
    void shouldAbleToFetchAllBooks() {
        Iterable<Book> all = bookRepository.findAll();
        Long totalBook = StreamSupport.stream(all.spliterator(), false).count();
        Assertions.assertEquals(totalBook,18);
    }

    @Test
    @Sql(scripts = {"classpath:insertInitialBookRecord.sql"})
    void shouldFindBookByTitle() {
        List<Book> bookList = bookRepository.findBooksByTitle("Test title");
        Assertions.assertEquals(bookList.size(),1);
    }

    @Test
    @Sql(scripts = {"classpath:insertInitialBookRecord.sql"})
    void shouldFindBookByTitleIgnoreCase() {
        List<Book> bookList = bookRepository.findBooksByTitleIgnoreCase("test title");
        Assertions.assertEquals(bookList.size(),1);
    }
}
