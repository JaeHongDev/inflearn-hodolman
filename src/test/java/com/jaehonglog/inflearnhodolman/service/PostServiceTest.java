package com.jaehonglog.inflearnhodolman.service;

import static org.junit.jupiter.api.Assertions.*;

import com.jaehonglog.inflearnhodolman.repository.PostRepository;
import com.jaehonglog.inflearnhodolman.request.PostCreate;
import org.assertj.core.api.Assertions;
import org.hibernate.event.spi.PostCollectionRecreateEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void tearDown(){
        this.postRepository.deleteAll();
    }
    @Test
    void 게시글_생성테스트(){
        postService.write(
                PostCreate.builder()
                        .title("제목입니다.")
                        .content("내용입니다.")
                        .build()
        );
        Assertions.assertThat(postRepository.count()).isEqualTo(1);
    }


    @Test
    void 게시글_조회_테스트(){
        postService.write(
                PostCreate.builder()
                        .title("제목입니다.")
                        .content("내용입니다.")
                        .build()
        );
        final var post = postService.get(1L);

        assertAll(
                () ->Assertions.assertThat(post.title()).isEqualTo("제목입니다."),
                () -> Assertions.assertThat(post.content()).isEqualTo("내용입니다."));
    }

}