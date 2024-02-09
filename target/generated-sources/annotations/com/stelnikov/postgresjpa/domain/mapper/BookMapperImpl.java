package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.domain.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-08T19:39:59-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
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
        bookDto.setAuthor( book.getAuthor() );

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
        book.setAuthor( bookDto.getAuthor() );

        return book;
    }
}
