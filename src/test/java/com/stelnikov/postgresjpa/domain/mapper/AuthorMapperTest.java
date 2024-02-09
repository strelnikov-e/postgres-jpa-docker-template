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

}