package com.example.shelfofshame.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
}
