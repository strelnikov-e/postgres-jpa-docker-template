package com.stelnikov.postgresjpa.repository;

import com.stelnikov.postgresjpa.TestDataUtil;
import com.stelnikov.postgresjpa.domain.domain.Author;
import com.stelnikov.postgresjpa.repository.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoImplIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(author.getId());
    }

    @Test
    public void findAllShouldReturnListOfAuthors() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        List<Author> result = underTest.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        author.setName("Updated Author");
        underTest.save(author);

        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(author.getName());
    }

    @Test
    public void testThatDeleteAuthorDeletesSuccessfully() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        underTest.delete(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isEmpty();
    }

}
