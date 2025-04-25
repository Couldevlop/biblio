package com.openlab.biblio.repository;

import com.openlab.biblio.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
