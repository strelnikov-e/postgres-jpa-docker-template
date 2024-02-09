package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.TestDataUtil;
import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorMapperTest {

    @Test
    public void shouldMapAuthorToAuthorDto() {
        Author author = TestDataUtil.createTestAuthorA();

        AuthorDto authorDto = AuthorMapper.INSTANCE.toAuthorDto(author);
        assertThat(authorDto).isNotNull();
        assertThat(authorDto.getId()).isEqualTo(author.getId());
        assertThat(authorDto.getName()).isEqualTo(author.getName());
        assertThat(authorDto.getAge()).isEqualTo(author.getAge());
    }

    @Test
    public void shouldMapAuthorDtoToAuthor() {
        Author author = TestDataUtil.createTestAuthorA();
        AuthorDto authorDto = AuthorMapper.INSTANCE.toAuthorDto(author);
        Author test = AuthorMapper.INSTANCE.toAuthor(authorDto);

        assertThat(test).isNotNull();
        assertThat(test.getId()).isEqualTo(authorDto.getId());
        assertThat(test.getName()).isEqualTo(authorDto.getName());
        assertThat(test.getAge()).isEqualTo(authorDto.getAge());
    }

}