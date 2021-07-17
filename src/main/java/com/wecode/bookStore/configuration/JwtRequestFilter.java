package com.wecode.bookStore.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.getHeaders("Access-Control-Allow-Headers");

        if (request.getRequestURI().equals("/api/v1/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenWithBearer = request.getHeader("Authorization");

        if (tokenWithBearer == null || !tokenWithBearer.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenWithBearer.substring(7);

        Authentication authentication = jwtUtil.validateToken(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
