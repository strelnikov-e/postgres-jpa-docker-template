package com.stelnikov.postgresjpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stelnikov.postgresjpa.TestDataUtil;
import com.stelnikov.postgresjpa.domain.dto.AuthorDto;
import com.stelnikov.postgresjpa.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AuthorControllerIntegrationTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorDto author = TestDataUtil.createTestAuthorDTOA();
        author.setId(null);

        String authorJson = objectMapper.writeValueAsString(author);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorDto testAuthorA = TestDataUtil.createTestAuthorDTOA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Author One")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }

    @Test
    public void testThatGetAllReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAllReturnsListOfAuthors() throws Exception {
        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].name").value("Author One")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].age").value(80)
        );
    }

    @Test
    public void testThatGetByIdReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetByIdReturnsAuthorWhenAuthorExist() throws Exception {
        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(testAuthorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Author One")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(80)
        );
    }

    @Test
    public void testThatGetByIdReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateReturnsHttpStatus404WhenNoAuthorExists() throws Exception {
        AuthorDto testAuthorDto = TestDataUtil.createTestAuthorDTOA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDto);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatUpdateReturnsHttpStatus200WhenAuthorExists() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(authorDto);

        AuthorDto test = TestDataUtil.createTestAuthorDTOB();
        String authorJson = objectMapper.writeValueAsString(test);

        mockMvc.perform(
                        MockMvcRequestBuilders.request(HttpMethod.PUT,
                                "/authors/" + authorDto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(authorDto);

        AuthorDto test = TestDataUtil.createTestAuthorDTOB();
        String authorJson = objectMapper.writeValueAsString(test);

        mockMvc.perform(
                        MockMvcRequestBuilders.request(HttpMethod.PUT,
                                        "/authors/" + authorDto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Author Two")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(24)
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus20Ok() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(authorDto);

        AuthorDto test = TestDataUtil.createTestAuthorDTOB();
        test.setId(null);
        test.setName("Updated");
        test.setAge(null);
        String authorJson = objectMapper.writeValueAsString(test);

        mockMvc.perform(
                        MockMvcRequestBuilders.request(HttpMethod.PUT,
                                        "/authors/" + authorDto.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authorJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(authorDto);

        AuthorDto test = TestDataUtil.createTestAuthorDTOA();
        test.setId(null);
        test.setName("Updated");
        String authorJson = objectMapper.writeValueAsString(test);

        mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.PUT,
                                "/authors/" + authorDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(authorDto.getId())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        );

    }

    @Test
    void testThatDeleteSuccessfullyReturnHttp204ForExistingAuthor() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDTOA();
        authorService.save(authorDto);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/authors/" + authorDto.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testThatDeleteSuccessfullyReturnHttp204ForNonExistingAuthor() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/authors/" + 99)
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}