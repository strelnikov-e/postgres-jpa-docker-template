package com.stelnikov.postgresjpa.domain.dto;

import java.util.Objects;

public class BookDto {

    private String isbn;
    private String title;
    private AuthorDto author;

    public BookDto() {
    }

    public BookDto(String isbn, String title, AuthorDto author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;
        BookDto bookDto = (BookDto) o;
        return getIsbn().equals(bookDto.getIsbn()) && getTitle().equals(bookDto.getTitle()) && Objects.equals(getAuthor(), bookDto.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getAuthor());
    }
}
