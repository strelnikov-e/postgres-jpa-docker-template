package com.stelnikov.postgresjpa.domain.dto;

import java.util.Objects;

public class AuthorDto {

    private Long id;
    private String name;
    private Integer age;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorDto)) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return getId().equals(authorDto.getId()) && getName().equals(authorDto.getName()) && Objects.equals(getAge(), authorDto.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge());
    }
}
