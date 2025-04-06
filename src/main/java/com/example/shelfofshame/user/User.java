package com.example.shelfofshame.user;

import com.example.shelfofshame.user.shelf.UserShelfItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    @Size(min=3,max=20)
    private String username;

    @Column(nullable = false)
    @Size(min=8,max=128)
    @NotBlank
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserShelfItem> shelfItems;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;
}
