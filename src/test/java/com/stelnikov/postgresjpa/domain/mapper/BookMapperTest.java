package com.stelnikov.postgresjpa.domain.mapper;

import com.stelnikov.postgresjpa.TestDataUtil;
import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.domain.entity.Author;
import com.stelnikov.postgresjpa.domain.entity.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookMapperTest {

    @Test
    public void shouldMapBookToBookDto() {
        Author author = TestDataUtil.createTestAuthorA();
        Book book = TestDataUtil.createTestBookA(author);

        BookDto bookDto = BookMapper.INSTANCE.toBookDto(book);
        assertThat(bookDto).isNotNull();
        assertThat(bookDto.getIsbn()).isEqualTo(book.getIsbn());
        assertThat(bookDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookDto.getAuthor().getId()).isEqualTo(book.getAuthor().getId());
    }

}