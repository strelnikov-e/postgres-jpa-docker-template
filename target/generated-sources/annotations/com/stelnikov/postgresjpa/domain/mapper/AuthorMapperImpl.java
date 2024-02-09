package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-08T19:06:33-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
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
}
