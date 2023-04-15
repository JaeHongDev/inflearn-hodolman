package com.jaehonglog.inflearnhodolman.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostCreate {

    @NotBlank(message = "제목이 필요합니다.")
    private String title;

    @NotBlank
    private String content;
}
