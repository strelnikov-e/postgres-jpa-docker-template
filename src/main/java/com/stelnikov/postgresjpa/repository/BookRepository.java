package com.stelnikov.postgresjpa.repository;

import com.stelnikov.postgresjpa.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, String>, PagingAndSortingRepository<Book, String> {
    void deleteByIsbn(String isbn);

    Page<Book> findAll(Pageable pageable);
}
