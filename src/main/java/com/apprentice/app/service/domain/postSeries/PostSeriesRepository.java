package com.apprentice.app.service.domain.postSeries;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostSeriesRepository extends JpaRepository<PostSeries, Integer> {
    List<PostSeries> findAllByWriter(String writer);
}
