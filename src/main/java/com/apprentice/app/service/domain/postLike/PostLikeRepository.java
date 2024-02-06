package com.apprentice.app.service.domain.postLike;

import com.apprentice.app.service.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeId> {
    Long countByPost(Post post);
}
