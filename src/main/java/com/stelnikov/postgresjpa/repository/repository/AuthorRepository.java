package com.stelnikov.postgresjpa.repository.repository;

import com.stelnikov.postgresjpa.domain.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
