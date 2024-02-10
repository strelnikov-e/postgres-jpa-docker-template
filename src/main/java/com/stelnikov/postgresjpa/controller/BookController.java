package com.stelnikov.postgresjpa.controller;

import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDto> addOrUpdate(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        bookDto.setIsbn(isbn);
        if (bookService.isExists(isbn)) {
            return new ResponseEntity<>(bookService.save(bookDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookService.save(bookDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getByIsbn(@PathVariable String isbn) {
        BookDto bookDto = bookService.findById(isbn);
        if (bookDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PatchMapping("/{isbn}")
    public ResponseEntity<BookDto> patch(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookDto.setIsbn(isbn);
        return new ResponseEntity<>(bookService.partialUpdate(bookDto), HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
