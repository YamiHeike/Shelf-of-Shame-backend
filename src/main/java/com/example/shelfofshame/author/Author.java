package com.example.shelfofshame.author;

import com.example.shelfofshame.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@Entity
@Table(
        name = "authors",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="unique_author",
                        columnNames = { "firstName", "lastName" }
                )
        })
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
