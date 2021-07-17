package com.wecode.bookStore.service;

import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.domain.dto.UserDto;
import com.wecode.bookStore.domain.entity.Book;
import com.wecode.bookStore.domain.entity.User;
import com.wecode.bookStore.repository.BookRepository;
import com.wecode.bookStore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UUID createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setId(null);

        User userCreated = userRepository.save(user);
        return userCreated.getId();
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if(Objects.isNull(user)) {
            throw new RuntimeException("user not exist " + email);
        }

        return modelMapper.map(user, UserDto.class);
    }

}
