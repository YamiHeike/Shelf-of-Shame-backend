package com.example.shelfofshame.book.genre;

import com.example.shelfofshame.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Book findByIsbn(String isbn);
    Book findByTitle(String title);
}
