package com.wecode.bookStore.controller;

import com.wecode.bookStore.domain.dto.BookDto;
import com.wecode.bookStore.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "Book Store API", tags = "Book API", produces = "application/json")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ApiOperation(value = "Get list of books", response = BookDto[].class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found resource")
    })
    @GetMapping("api/v1/books")
    public ResponseEntity <List<BookDto>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

}
