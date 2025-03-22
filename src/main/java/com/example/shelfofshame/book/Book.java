package com.example.shelfofshame.book;

import com.example.shelfofshame.author.Author;
import com.example.shelfofshame.book.genre.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@Entity
@ToString(exclude = {"authors", "genres"})
@EqualsAndHashCode(exclude = {"authors", "genres"})
@Table(name = "books")
public class Book {
    @Id @Pattern(regexp = "^[0-9]{10}$", message = "ISBN-10 must be exactly 10 digits.")
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
    private int numberOfPages;
    @ManyToMany
    @JoinTable(
            name = "books_genres",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Genre> genres;
}
