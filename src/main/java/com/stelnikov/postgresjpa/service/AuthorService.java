package com.stelnikov.postgresjpa.service;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;


public interface AuthorService {

    public AuthorDto save(AuthorDto authorDto);
}
