package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import com.stelnikov.postgresjpa.domain.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T23:59:41-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Homebrew)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setIsbn( book.getIsbn() );
        bookDto.setTitle( book.getTitle() );
        bookDto.setAuthor( authorToAuthorDto( book.getAuthor() ) );

        return bookDto;
    }

    @Override
    public Book toBook(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setIsbn( bookDto.getIsbn() );
        book.setTitle( bookDto.getTitle() );
        book.setAuthor( authorDtoToAuthor( bookDto.getAuthor() ) );

        return book;
    }

    protected AuthorDto authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( author.getId() );
        authorDto.setName( author.getName() );
        authorDto.setAge( author.getAge() );

        return authorDto;
    }

    protected Author authorDtoToAuthor(AuthorDto authorDto) {
        if ( authorDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDto.getId() );
        author.setName( authorDto.getName() );
        author.setAge( authorDto.getAge() );

        return author;
    }
}
