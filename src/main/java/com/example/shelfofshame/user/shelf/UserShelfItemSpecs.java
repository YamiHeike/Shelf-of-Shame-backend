package com.example.shelfofshame.user.shelf;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class UserShelfItemSpecs {
    public Specification<UserShelfItem> hasStatus(Status status) {
        return (root, query, builder) -> builder.equal(root.get("status"), status);
    }

    public Specification<UserShelfItem> hasDifficulty(Integer minDifficulty, Integer maxDifficulty) {
        int min = minDifficulty == null ? 1 : minDifficulty;
        int max = maxDifficulty == null ? 10 : maxDifficulty;
        return (root, query, builder) -> builder.between(root.get("difficulty"), min, max);
    }

    public Specification<UserShelfItem> genresIn(List<String> genres) {
        return (root, query, builder) -> root.join("genres").get("name").in(genres);
    }
}
