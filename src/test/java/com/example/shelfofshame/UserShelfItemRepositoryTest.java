package com.example.shelfofshame;

import com.example.shelfofshame.author.Author;
import com.example.shelfofshame.author.AuthorRepository;
import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.book.BookRepository;
import com.example.shelfofshame.book.genre.Genre;
import com.example.shelfofshame.book.genre.GenreRepository;
import com.example.shelfofshame.user.User;
import com.example.shelfofshame.user.UserRepository;
import com.example.shelfofshame.user.UserRole;
import com.example.shelfofshame.user.shelf.Status;
import com.example.shelfofshame.user.shelf.UserShelfItem;
import com.example.shelfofshame.user.shelf.UserShelfItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class UserShelfItemRepositoryTest {
    @Autowired
    UserShelfItemRepository userShelfItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;

    @Test
    @Transactional
    public void shouldSaveUserShelfItem() {
        User user = User
                .builder()
                .username("test")
                .email("test@example.com")
                .password("password")
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
        Author author = Author.builder()
                .firstName("John")
                .lastName("Doe")
                .books(new HashSet<>())
                .build();
        authorRepository.save(author);
        Genre genre = Genre.builder()
                .name("test")
                .books(new HashSet<>())
                .build();
        genreRepository.save(genre);
        Book book = Book
                .builder()
                .title("test")
                .isbn("1111111111")
                .description("description")
                .authors(Set.of(author))
                .numberOfPages(500)
                .genres(Set.of(genre))
                .build();
        bookRepository.save(book);
        author.getBooks().add(book);
        genre.getBooks().add(book);

        UserShelfItem userShelfItem = UserShelfItem
                .builder()
                .user(user)
                .notes("notes")
                .difficulty(5)
                .user(entityManager.find(User.class, user.getId()))
                .book(book)
                .status(Status.READING)
                .build();
        entityManager.persist(userShelfItem);
        userShelfItemRepository.save(userShelfItem);
        UserShelfItem savedItem = entityManager.find(UserShelfItem.class, userShelfItem.getId());
        assertNotNull(savedItem);
        assertEquals(savedItem, userShelfItem);
        assertEquals(savedItem.getDifficulty(), userShelfItem.getDifficulty());
        assertEquals(savedItem.getStatus(), userShelfItem.getStatus());
        assertEquals(savedItem.getUser(), userShelfItem.getUser());
    }
}
