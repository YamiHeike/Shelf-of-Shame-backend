package com.example.shelfofshame.author;

import com.example.shelfofshame.author.dto.AuthorDto;
import com.example.shelfofshame.author.dto.CreateAuthorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Add a new author", description = "Creates a new author object in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author successfully created"),
            @ApiResponse(responseCode = "400", description = "Author already exists or invalid input")
    })
    @PostMapping("/new")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description =
            "Data Transfer Object representing an author used for communication between client and server.", required = true) CreateAuthorDto newAuthor) {
        AuthorDto created = authorService.addAuthor(newAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Get all authors", description = "Returns a list of all authors available in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a list of authors")
    })
    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

}