package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@Entity
@Table(name = "user_shelf_items")
public class UserShelfItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @NotNull
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_isbn", nullable = false, updatable = false)
    @NotNull
    private Book book;
    @Column
    private String notes;
    @Column
    @Min(value = 1, message="Difficulty scale: 1-10")
    @Max(value = 10, message="Difficulty scale: 1-10")
    private Integer difficulty;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
