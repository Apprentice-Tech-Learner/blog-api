package com.apprentice.app.service.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findAllByStatus(int status);
    @Query("SELECT DISTINCT p FROM Post p LEFT OUTER JOIN FETCH p.tags t WHERE p.post_id = :id")
    Post findByIdUsingFetchJoin(String id);
}
