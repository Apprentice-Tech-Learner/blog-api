package com.apprentice.app.service.domain.PostSeries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_series")
public class PostSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int series_id;
    @Column(length = 50)
    private String series_name;
    @Column(length = 50)
    private String writer;
}
