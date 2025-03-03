package com.example.shelfofshame.config;

import com.example.shelfofshame.errors.ErrorDto;
import com.example.shelfofshame.errors.FieldErrorDto;
import com.example.shelfofshame.errors.ValidationErrorDto;
import com.example.shelfofshame.errors.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAppException(AppException e) {
        return ResponseEntity.status(e.getStatus())
                .body(ErrorDto.builder().message(e.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleValidationException(MethodArgumentNotValidException e) {
        List<FieldErrorDto> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> FieldErrorDto.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .toList();
        ValidationErrorDto validationErrorDto = ValidationErrorDto.builder()
                .message("Validation failed")
                .errors(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDto);
    }
}
