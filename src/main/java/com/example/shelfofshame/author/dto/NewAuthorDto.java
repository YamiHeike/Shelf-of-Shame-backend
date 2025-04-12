package com.example.shelfofshame.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class NewAuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
}
