package com.example.shelfofshame.book;

import com.example.shelfofshame.author.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@Entity
@Table(name = "books")
public class Book {
    @Id @Size(min = 10, max = 10)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;
    @Column
    private String description;
    @Column
    private String coverUrl;
    @Column
    private int numberOfPages;
}
