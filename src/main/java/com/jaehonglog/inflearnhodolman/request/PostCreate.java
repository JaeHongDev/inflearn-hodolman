package com.jaehonglog.inflearnhodolman.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class PostCreate {
    @NotBlank(message = "제목이 필요합니다.")
    private String title;
    @NotBlank
    private String content;


    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
