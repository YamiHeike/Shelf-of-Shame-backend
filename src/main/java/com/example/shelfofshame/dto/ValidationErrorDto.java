package com.example.shelfofshame.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrorDto {
    private String message;
    private List<FieldErrorDto> errors;
}
