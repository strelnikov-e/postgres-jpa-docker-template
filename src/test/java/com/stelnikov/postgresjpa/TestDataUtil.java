package com.stelnikov.postgresjpa;


import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import com.stelnikov.postgresjpa.domain.entity.Book;

public class TestDataUtil {

    private TestDataUtil() {}

    public static Author createTestAuthorA() {
        return new Author(1L, "Author One", 80);
    }

    public static Author createTestAuthorB() {
        return new Author(2L, "Author Two", 24);
    }

    public static Author createTestAuthorC() {
        return new Author(3L, "Author Three", 47);
    }

    public static AuthorDto createTestAuthorDTOA() {
        return new AuthorDto(1L, "Author One", 80);
    }

    public static AuthorDto createTestAuthorDTOB() {
        return new AuthorDto(2L, "Author Two", 24);
    }

    public static AuthorDto createTestAuthorDTOC() {
        return new AuthorDto(3L, "Author Three", 47);
    }

    public static Book createTestBookA(final Author author) {
        return new Book("111-1-111-1000-0", "Book One", author);
    }

    public static Book createTestBookB(final Author author) {
        return new Book("222-2-222-2000-0", "Book Two", author);
    }

    public static Book createTestBookC(final Author author) {
        return new Book("333-3-333-3000-0", "Book Three", author);
    }

    public static BookDto createTestBookDTOA(final Author author) {
        return new BookDto("111-1-111-1000-0", "Book One", author);
    }

    public static BookDto createTestBooDTOkB(final Author author) {
        return new BookDto("222-2-222-2000-0", "Book Two", author);
    }

    public static BookDto createTestBookDTOC(final Author author) {
        return new BookDto("333-3-333-3000-0", "Book Three", author);
    }
}
