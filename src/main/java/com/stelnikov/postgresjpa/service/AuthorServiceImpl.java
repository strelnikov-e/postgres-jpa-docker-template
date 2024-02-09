package com.stelnikov.postgresjpa.service;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import com.stelnikov.postgresjpa.domain.mapper.AuthorMapper;
import com.stelnikov.postgresjpa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    AuthorMapper mapper = AuthorMapper.INSTANCE;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author author = mapper.toAuthor(authorDto);
        Author saved =  authorRepository.save(author);

        return mapper.toAuthorDto(saved);
    }
}
