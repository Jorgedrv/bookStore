package com.wecode.bookStore.controller;

import com.wecode.bookStore.domain.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("api/v1/books")
    public ResponseEntity <List<BookDto>> getBooks() {
        BookDto book = BookDto.builder().title("My first book title").build();
        List<BookDto> list = new ArrayList<>();
        list.add(book);
        return ResponseEntity.ok(list);
    }

}
