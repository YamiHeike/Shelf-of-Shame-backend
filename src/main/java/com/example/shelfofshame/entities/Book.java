package com.example.shelfofshame.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @Column
    private String coverUrl;
    @Column
    private int numberOfPages;
}
