package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserShelfItemRepository extends JpaRepository<UserShelfItem, Long> {
    public List<UserShelfItem> findByUser(User user);
    public List<UserShelfItem> findByUserAndStatus(User user, Status status);
    public List<UserShelfItem> findByUserAndDifficulty(User user, int difficulty);
    public List<UserShelfItem> findByUserAndDifficultyAndStatus(User user, int difficulty, Status status);
    public List<UserShelfItem> deleteByUserAndBook(User user, Book book);
    boolean existsByUserAndBook(User user, Book book);
}
