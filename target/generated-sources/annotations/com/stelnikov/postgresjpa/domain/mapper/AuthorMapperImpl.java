package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-09T23:09:38-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Homebrew)"
)
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorDto toAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( author.getId() );
        authorDto.setName( author.getName() );
        authorDto.setAge( author.getAge() );

        return authorDto;
    }

    @Override
    public Author toAuthor(AuthorDto authorDto) {
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
