package com.stelnikov.postgresjpa.controller;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authorDto) {
        AuthorDto toSave = authorService.save(authorDto);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }


}
