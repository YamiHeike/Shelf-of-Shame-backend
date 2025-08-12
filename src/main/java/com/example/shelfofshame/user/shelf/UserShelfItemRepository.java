package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.book.Book;
import com.example.shelfofshame.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserShelfItemRepository extends JpaRepository<UserShelfItem, Long>, JpaSpecificationExecutor<UserShelfItem> {
    public List<UserShelfItem> findByUser(User user);
    public boolean existsByUserAndBook(User user, Book book);
    public boolean existsByUserAndId(User user, Long id);
}
