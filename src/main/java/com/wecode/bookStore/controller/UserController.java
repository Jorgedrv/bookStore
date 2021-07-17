package com.wecode.bookStore.controller;

import com.wecode.bookStore.configuration.JwtUtil;
import com.wecode.bookStore.domain.dto.AuthenticationRequest;
import com.wecode.bookStore.domain.dto.AuthenticationResponse;
import com.wecode.bookStore.domain.dto.UserDto;
import com.wecode.bookStore.service.UserDetailService;
import com.wecode.bookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://course-book-store.herokuapp.com", maxAge = 3600)
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final UserService userService;
    private final JwtUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Username or password is incorrect");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
        String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse("Bearer " + token));
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> createUser(@RequestBody UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }
}
