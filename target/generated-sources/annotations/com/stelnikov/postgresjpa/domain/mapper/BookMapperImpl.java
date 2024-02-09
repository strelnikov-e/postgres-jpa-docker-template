package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.domain.entity.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-08T19:17:55-0500",
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
}
