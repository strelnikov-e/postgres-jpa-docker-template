package com.stelnikov.postgresjpa.service;

import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import com.stelnikov.postgresjpa.domain.mapper.AuthorMapper;
import com.stelnikov.postgresjpa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<AuthorDto> findAll() {

        return authorRepository.findAll().stream()
                .map((author -> mapper.toAuthorDto(author)))
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorDto> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable).map(mapper::toAuthorDto);
    }

    @Override
    public AuthorDto findById(Long id) {
        Author result = authorRepository.findById(id)
                .orElse(null);
        return mapper.toAuthorDto(result);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorDto update(Long id, AuthorDto authorDto) {
        authorDto.setId(id);
        return this.save(authorDto);
    }

    @Override
    public AuthorDto partialUpdate(Long id, AuthorDto authorDto) {
        authorDto.setId(id);
        AuthorDto result = findById(id);
        Optional.ofNullable(authorDto.getName()).ifPresent(result::setName);
        Optional.ofNullable(authorDto.getAge()).ifPresent(result::setAge);

        return save(result);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
