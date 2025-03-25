package com.example.shelfofshame.book.genre;

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
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;
}
