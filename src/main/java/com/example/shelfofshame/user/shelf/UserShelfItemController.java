package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.AuthenticatedUserProvider;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.shelf.dto.AddBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.AddNewBookToShelfDto;
import com.example.shelfofshame.user.shelf.dto.UserShelfItemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/shelf")
public class UserShelfItemController {
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final UserShelfItemService userShelfItemService;
    private final UserShelfItemMapper userShelfItemMapper;


    @Operation(
            summary = "Add existing book to user shelf",
            description = "Adds an existing book by ISBN to the authenticated user's shelf with optional notes, difficulty, and status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book successfully added to shelf"),
            @ApiResponse(responseCode = "400", description = "Validation error or invalid input data"),
            @ApiResponse(responseCode = "404", description = "Book with the specified ISBN not found"),
            @ApiResponse(responseCode = "409", description = "Book is already in the user's shelf"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - user not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add")
    public ResponseEntity<UserShelfItemDto> addNewBookToShelf(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data Transfer Object with ISBN-10 and book metadata") AddBookToShelfDto addBookToShelfDto,
                                                              @Parameter(hidden = true) Principal principal) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        log.info("Add new book to shelf started for user {}", user.getUsername());
        UserShelfItem userShelfItem = userShelfItemService.addExistingBook(addBookToShelfDto, user);
        UserShelfItemDto userShelfItemDto = userShelfItemMapper.toUserShelfItemDto(userShelfItem);
        return ResponseEntity.ok(userShelfItemDto);
    }

    @Operation(
            summary = "Create new book and add it to user shelf",
            description = "Creates a new book with detailed information and adds it to the authenticated user's shelf."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "New book created and added to shelf successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error or invalid author/genre/book data"),
            @ApiResponse(responseCode = "409", description = "Book with the given ISBN already exists"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - user not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add-new")
    public ResponseEntity<UserShelfItemDto> createAndBookAddToShelf(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data Transfer Object with new book and shelf item details") AddNewBookToShelfDto addNewBookToShelfDto,
                                                                    @Parameter(hidden = true) Principal principal) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        log.info("Creating new book and adding it to shelf started for user {}", user.getUsername());
        UserShelfItemDto userShelfItem = userShelfItemService.addNewBookToShelf(addNewBookToShelfDto, user);
        return ResponseEntity.ok(userShelfItem);
    }

    @Operation(
            summary = "Retrieves all user shelf items",
            description = "Returns a list of all shelf items associated with a specific user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a list of user shelf items"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - user not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<UserShelfItemDto>> getUserShelfItems(@Parameter(hidden = true) Principal principal) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        List<UserShelfItemDto> userShelfItems = userShelfItemService.findBooksFor(user);
        return ResponseEntity.ok(userShelfItems);
    }

    @Operation(
            summary = "Retrieves a page of user shelf items",
            description = """
                    Returns a paginated list of user shelf items belonging to the authenticated user.\s
                    Pagination parameters (page and size) are passed as query parameters.\s"""
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a list of user shelf items"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - user not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Parameters({
            @Parameter(
                    name = "page",
                    description = "Page number (0-based)",
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "integer", defaultValue = "0")
            ),
            @Parameter(
                    name = "size",
                    description = "Number of items per page",
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "integer", defaultValue = "20")
            )
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/pages")
    public ResponseEntity<Page<UserShelfItemDto>> getUserShelfItemsPage(
            @Parameter(hidden = true) Principal principal,
            Pageable pageable
    ) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        var page = userShelfItemService.findUserShelfItemsPage(user, pageable);
        return ResponseEntity.ok(page);
    }

    @Operation(
            summary = "Get a user's shelf item by ID",
            description = "Returns the shelf item belonging to the currently authenticated user by item ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved shelf item"),
            @ApiResponse(responseCode = "400", description = "Item exists but does not belong to the current user's shelf"),
            @ApiResponse(responseCode = "404", description = "Shelf item not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<UserShelfItemDto> findUserShelfItem(
            @Parameter(hidden = true) Principal principal,
            @Parameter(description = "ID of the shelf item", example = "42") @PathVariable Long id) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        UserShelfItemDto shelfItem = userShelfItemService.findUserShelfItemById(user, id);
        return ResponseEntity.ok(shelfItem);
    }

    @Operation(
            summary = "Mark user shelf item as read",
            description = "Changes status of user shelf item associated with the ID to read"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marked shelf item as read"),
            @ApiResponse(responseCode = "400", description = "Item exists but does not belong to the current user's shelf"),
            @ApiResponse(responseCode = "404", description = "Shelf item not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}/mark-read")
    public ResponseEntity<UserShelfItemDto> markAsRead(
            @Parameter(hidden = true) Principal principal,
            @Parameter(description = "ID of the shelf item", example = "42") @PathVariable Long id
    ) {
        User user = authenticatedUserProvider.getCurrentUser(principal);
        UserShelfItemDto shelfItem = userShelfItemService.markAsRead(user, id);
        return ResponseEntity.ok(shelfItem);
    }
}
