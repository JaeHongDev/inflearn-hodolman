package com.jaehonglog.inflearnhodolman.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
public
record PostEdit(
        @NotBlank(message = "제목이 필요합니다.") String title,
        @NotBlank String content){
}
