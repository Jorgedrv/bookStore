package com.wecode.bookStore.integration.test;

import com.wecode.bookStore.StartApplication;
import com.wecode.bookStore.configuration.JwtUtil;
import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.domain.dto.UserDto;
import com.wecode.bookStore.mock.UserMock;
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
import java.util.UUID;

@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IUserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql(scripts = {"classpath:insertInitialUserRecord.sql"})
    void testControllerShouldReturnBookList() {
        UUID uuid = UUID.randomUUID();
        String url = "http://localhost:" + port + "/api/v1/register";
        UUID id = testRestTemplate.postForObject(url, UserMock.getUserDto(uuid), UUID.class);
        Assertions.assertThat(id).isNotNull();
    }
}

