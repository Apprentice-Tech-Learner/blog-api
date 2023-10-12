package com.apprentice.app.service.domain.tag;

import com.apprentice.app.service.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostTagRepository extends JpaRepository<PostTag, PostTagId> {
    List<PostTag> findAllByPost(Post post);
}
