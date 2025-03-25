package com.example.shelfofshame.author;

import com.example.shelfofshame.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@ToString(exclude = {"books"})
@EqualsAndHashCode(exclude = {"books"})
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
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
