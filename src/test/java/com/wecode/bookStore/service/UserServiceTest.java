package com.wecode.bookStore.service;

import com.wecode.bookStore.domain.dto.UserDto;
import com.wecode.bookStore.mock.UserMock;
import com.wecode.bookStore.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    public static final String EMAIL = "jorgeddrv@gmail.com";
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserIdWhenCallUser() {
        UUID id = UUID.randomUUID();
        when(userRepository.save(any())).thenReturn(UserMock.getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(UserMock.getUser(id));

        UUID uuid = userService.createUser(UserMock.getUserDto(id));

        Assertions.assertThat(uuid).isNotNull();
        Assertions.assertThat(uuid).isEqualTo(id);
    }

    @Test
    void shouldReturnUserWhenExistEmail() {
        UUID id = UUID.randomUUID();
        when(userRepository.findByEmail(EMAIL)).thenReturn(UserMock.getUser(id));
        when(modelMapper.map(any(), any())).thenReturn(UserMock.getUserDto(id));

        UserDto email = userService.getUserByEmail(EMAIL);

        Assertions.assertThat(email).isNotNull();
        Assertions.assertThat(email.getEmail()).isEqualTo(EMAIL);
    }


    @Test
    public void shouldThrowErrorWhenEmailIsNotExist() {
        assertThatThrownBy(() -> userService.getUserByEmail(null)).isInstanceOf(RuntimeException.class);
    }
}
