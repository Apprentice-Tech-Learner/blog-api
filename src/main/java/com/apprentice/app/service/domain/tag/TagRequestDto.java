package com.apprentice.app.service.domain.tag;

public class TagRequestDto {
    private String tag;

    public TagRequestDto(String tag) {
        this.tag = tag;
    }

    public Tag toEntity() {
        return Tag.builder()
                .name(tag)
                .build();
    }
}
