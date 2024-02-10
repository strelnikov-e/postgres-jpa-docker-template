package com.stelnikov.postgresjpa.service;

import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.domain.entity.Book;
import com.stelnikov.postgresjpa.domain.mapper.BookMapper;
import com.stelnikov.postgresjpa.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    AuthorService authorService;
    BookMapper mapper = BookMapper.INSTANCE;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public BookDto save(BookDto bookDto) {
        Book result = bookRepository.save(mapper.toBook(bookDto));
        return mapper.toBookDto(result);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookDto partialUpdate(BookDto bookDto) {
        Book toSave = mapper.toBook(bookDto);
        Book saved = bookRepository.findById(bookDto.getIsbn()).map(result -> {
                Optional.ofNullable(toSave.getTitle()).ifPresent(result::setTitle);
                Optional.ofNullable(toSave.getAuthor()).ifPresent(result::setAuthor);
                return result;
        }
        ).orElse(null);

        return mapper.toBookDto(saved);
    }

    @Override
    public BookDto findById(String isbn) {
        Book book = bookRepository.findById(isbn).orElse(null);
        if (book == null) return null;
        return mapper.toBookDto(book);
    }

    @Override
    @Transactional
    public void delete(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public List<BookDto> findAll() {

        return bookRepository.findAll().stream()
                .map(book -> mapper.toBookDto(book))
                .collect(Collectors.toList());
    }
}
