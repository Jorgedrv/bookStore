package com.wecode.bookStore.repository;

import com.wecode.bookStore.domain.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class RepositoryUserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(scripts = {"classpath:insertInitialUserRecord.sql"})
    void shouldFindUserByEmail() {
        User user = userRepository.findByEmail("jorgeddrv@gmail.com");
        Assertions.assertEquals(user.getName(), "Jorge");
    }
}
