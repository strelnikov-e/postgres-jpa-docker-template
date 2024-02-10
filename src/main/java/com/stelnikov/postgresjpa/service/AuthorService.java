package com.stelnikov.postgresjpa.service;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface AuthorService {

    public AuthorDto save(AuthorDto authorDto);

    List<AuthorDto> findAll();

    AuthorDto findById(Long id);

    boolean isExists(Long id);

    AuthorDto update(Long id, AuthorDto authorDto);

    void delete(Long id);

    AuthorDto partialUpdate(Long id, AuthorDto authorDto);

    Page<AuthorDto> findAll(Pageable pageable);
}
