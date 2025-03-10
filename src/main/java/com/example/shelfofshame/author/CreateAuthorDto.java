package com.example.shelfofshame.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class CreateAuthorDto {
    private String firstName;
    private String lastName;
}
