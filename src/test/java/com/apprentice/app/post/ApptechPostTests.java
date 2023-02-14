package com.apprentice.app.post;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.apprentice.app.post.entity.ApptechPost;
import com.apprentice.app.post.entity.ApptechPostRepository;

import java.util.List;

@SpringBootTest
public class ApptechPostTests {

    @Autowired
    ApptechPostRepository postRepository;

    @Test
    void save() {
        ApptechPost params = ApptechPost.builder()
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .userIdInsert("누이하나")
                .hits(0)
                .deleteYn('N')
                .build();

        postRepository.save(params);

//        ApptechPost entity = postRepository.findById((long) 1).get();
//        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
//        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
//        assertThat(entity.getUserIdInsert()).isEqualTo("누이하나");
    }

    @Test
    void findAll() {
        long postsCount = postRepository.count();

        List<ApptechPost> posts = postRepository.findAll();
    }

    @Test
    void delete() {
        ApptechPost entity = postRepository.findById((long) 2).get();

        postRepository.delete(entity);
    }
}
