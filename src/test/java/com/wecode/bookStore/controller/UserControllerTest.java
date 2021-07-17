package com.wecode.bookStore.controller;

import com.wecode.bookStore.mock.UserMock;
import com.wecode.bookStore.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testControllerShouldReturnUserUuid() {
        UUID uuid = UUID.randomUUID();
        when(userService.createUser(UserMock.getUserDto(uuid))).thenReturn(uuid);
        ResponseEntity<UUID> uuidResponse = userController.createUser(UserMock.getUserDto(uuid));
        Assertions.assertThat(uuidResponse).isNotNull();
    }
}
