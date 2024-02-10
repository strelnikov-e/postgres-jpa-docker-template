package com.stelnikov.postgresjpa.service;

import com.stelnikov.postgresjpa.domain.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDto save(BookDto bookDto);

    boolean isExists(String isbn);

    List<BookDto> findAll();

    BookDto findById(String isbn);

    BookDto partialUpdate(BookDto bookDto);

    void delete(String isbn);

    Page<BookDto> findAll(Pageable pageable);
}
