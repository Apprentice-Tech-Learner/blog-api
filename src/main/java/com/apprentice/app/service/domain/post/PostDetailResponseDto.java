package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.member.MemberResponseDto;
import com.apprentice.app.service.domain.postLike.PostLike;
import com.apprentice.app.service.domain.postSeries.PostSeries;
import com.apprentice.app.service.domain.postSeries.PostSeriesResponseDto;
import com.apprentice.app.service.domain.tag.PostTag;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDetailResponseDto {
    private String post_id;
    private String title;
    private String content;
    private String thumbnail;
    private String writer;
    private int status;
    private String description;
    private LocalDateTime created;

    private int is_writer;
    private int is_follower;

    private List<PostTag> tags;
    private long likes;
    private boolean be_liked;

    private PostSeriesResponseDto series;
    private MemberResponseDto user;

    public PostDetailResponseDto(Post entity) {
        this.post_id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.thumbnail = entity.getThumbnail();
        this.writer = entity.getWriter();
        this.status = entity.getStatus();
        this.description = entity.getDescription();
        this.created = entity.getCreated();
        this.tags = entity.getTags();
    }

    public void setLikeInfo(long cnt, boolean isLiked) {
        this.likes = cnt;
        this.be_liked = isLiked;
    }

    public void setPostSeries(PostSeriesResponseDto series) {
        this.series = series;
    }
    public void setUser(MemberResponseDto user) { this.user = user; }
    public void setRelation(String login_user) {
        // TODO : is_follower 세팅 필요
        is_follower =  0;
        is_writer = writer.equals(login_user) ? 1 : 0;
    }
}
