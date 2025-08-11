package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserShelfItemSpecs {
    public static Specification<UserShelfItem> hasStatus(Status status) {
        return (root, query, builder) -> builder.equal(root.get("status"), status);
    }

    public static Specification<UserShelfItem> hasDifficulty(Integer minDifficulty, Integer maxDifficulty) {
        int min = minDifficulty == null ? 1 : minDifficulty;
        int max = maxDifficulty == null ? 10 : maxDifficulty;
        return (root, query, builder) -> builder.between(root.get("difficulty"), min, max);
    }

    public static Specification<UserShelfItem> genresIn(List<String> genres) {
        return (root, query, builder) -> {
            query.distinct(true);
            return root.join("genres").get("name").in(genres);
        };

    }

    public static Specification<UserShelfItem> belongsToUser(User user) {
        return (root, query, builder) -> builder.equal(root.get("user"), user);
    }
}
