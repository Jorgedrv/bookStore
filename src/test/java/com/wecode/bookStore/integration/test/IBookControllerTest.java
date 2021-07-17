package com.wecode.bookStore.integration.test;

import com.wecode.bookStore.StartApplication;
import com.wecode.bookStore.configuration.JwtUtil;
import com.wecode.bookStore.domain.dto.BookDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IBookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUpHeader() {
        String token = jwtUtil.generateToken(new User("Jorgeddrv@gmail.com", passwordEncoder.encode("password"), new ArrayList<>()));
        testRestTemplate.getRestTemplate().setInterceptors(Collections.singletonList(((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + token);
            return execution.execute(request, body);
        })));
    }

    @Test
    @Sql(scripts = {"classpath:insertInitialBookRecord.sql"})
    void testControllerShouldReturnBookList() {
        String url = "http://localhost:" + port + "/api/v1/books";
        BookDto[] listOfBooks = testRestTemplate.getForObject(url, BookDto[].class);
        Assertions.assertThat(listOfBooks).isNotNull();
        Assertions.assertThat(listOfBooks.length).isEqualTo(18);
    }

    @Test
    @Sql(scripts = {"classpath:insertInitialBookRecord.sql"})
    void testControllerShouldReturnBookByTitle() {
        String url = "http://localhost:" + port + "/api/v1/books/TEST TITLE";
        BookDto[] listOfBooks = testRestTemplate.getForObject(url, BookDto[].class);
        Assertions.assertThat(listOfBooks).isNotNull();
        Assertions.assertThat(listOfBooks.length).isEqualTo(1);
    }
}
