package com.stelnikov.postgresjpa.controller;

import com.stelnikov.postgresjpa.domain.entity.Author;
import com.stelnikov.postgresjpa.domain.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    Logger log = LoggerFactory.getLogger(getClass());

    @PutMapping("/{isbn}")
    public Book addOrUpdate(@PathVariable String isbn, @RequestBody Book book) {

        return book;
    }

    @GetMapping("/")
    public Book getAll() {
        return new Book("111-0-11-1241-5", "Some book", new Author(1L, "Some Author", 65));
    }

    @GetMapping("/{isbn}")
    public Book getByIsbn(@PathVariable String isbn) {

        return new Book();
    }

    @PatchMapping("/{isbn}")
    public Book patch(@PathVariable String isbn, @RequestBody Book book) {
        return new Book();
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
