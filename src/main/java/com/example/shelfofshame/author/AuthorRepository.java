package com.example.shelfofshame.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findById(long id);
    Author findByFirstNameAndLastName(String firstName, String lastName);
    Author findByFirstName(String firstName);
    Author findByLastName(String lastName);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}