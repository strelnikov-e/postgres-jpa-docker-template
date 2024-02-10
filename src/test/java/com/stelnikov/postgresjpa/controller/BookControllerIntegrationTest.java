package com.stelnikov.postgresjpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stelnikov.postgresjpa.TestDataUtil;
import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.domain.dto.BookDto;
import com.stelnikov.postgresjpa.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class BookControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BookService bookService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testThatSaveOrUpdateReturnHttp201BookCreated() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOB();
        BookDto bookDto = TestDataUtil.createTestBookDTOC(authorDto);

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void testThatSaveOrUpdateReturnHttp200BookUpdated() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);

        bookService.save(bookDto);

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatSaveOrUpdateReturnCorrectBook() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor())
        );
    }

    @Test
    void testThatSaveOrUpdateReturnCorrectBookUpdated() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);
        BookDto saved = bookService.save(bookDto);
        bookDto.setIsbn("999-9-99-9999-0");
        bookDto.setTitle("Updated");

        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + saved.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(saved.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author").value(saved.getAuthor())
        );
    }

    @Test
    void testThatGetAllReturnHttp200Successfully() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatGetAllReturnCorrectListOfBooks() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);
        BookDto saved = bookService.save(bookDto);


        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(saved.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(saved.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value(saved.getAuthor())
        );
    }

    @Test
    void testThatGetByIdReturnHttp404NotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/123")
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testThatGetByIdReturnHttp200Successfully() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDTOA(TestDataUtil.createTestAuthorDTOA());
        bookService.save(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookDto.getIsbn())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatGetByIdReturnBookSuccessfully() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDTOA(TestDataUtil.createTestAuthorDTOA());
        bookService.save(bookDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookDto.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor())
        );
    }

    @Test
    void testThatPartialUpdateReturnHttp200BookUpdated() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);
        bookService.save(bookDto);

        bookDto.setTitle("Updated");
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testThatPartialUpdateReturnCorrectBook() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);

        bookDto.setIsbn("999-9-99-9999-0");
        bookDto.setTitle("Updated");
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.author").value(bookDto.getAuthor())
        );
    }

    @Test
    void testThatPartialUpdateReturnHttp404BookNotFound() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        BookDto bookDto = TestDataUtil.createTestBookDTOA(authorDto);
        bookService.save(bookDto);

        bookDto.setTitle("Updated");
        String bookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/books/" + 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testThatDeleteNonExistingBookReturns204NoContent() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/" + 123)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testThatDeleteExistingBookReturns204NoContent() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDTOA(null);
        bookService.save(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}