package com.jaehonglog.inflearnhodolman.response;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public record PostResponse(Long id, String title, String content) {

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}
