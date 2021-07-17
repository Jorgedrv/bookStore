package com.wecode.bookStore.mock;


import com.wecode.bookStore.domain.dto.UserDto;
import com.wecode.bookStore.domain.entity.User;

import java.util.UUID;

public class UserMock {

    public static User getUser(UUID id) {
        return User.builder()
                .id(id)
                .email("jorgeddrv@gmail.com")
                .name("Jorge")
                .password("password")
                .build();
    }

    public static UserDto getUserDto(UUID id) {
        return UserDto.builder()
                .id(id)
                .email("jorgeddrv@gmail.com")
                .name("Jorge")
                .password("password")
                .build();
    }
}

