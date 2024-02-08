package com.stelnikov.postgresjpa.repository.repository;

import com.stelnikov.postgresjpa.domain.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
