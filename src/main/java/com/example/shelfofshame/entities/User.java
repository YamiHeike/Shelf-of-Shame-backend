package com.example.shelfofshame.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    private String password;

    @Column(nullable = false)
    private String role;
}
