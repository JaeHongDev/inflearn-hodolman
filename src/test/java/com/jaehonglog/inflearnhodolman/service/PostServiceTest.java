package com.jaehonglog.inflearnhodolman.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.jaehonglog.inflearnhodolman.entity.Posts;
import com.jaehonglog.inflearnhodolman.repository.PostRepository;
import com.jaehonglog.inflearnhodolman.request.PostCreate;
import com.jaehonglog.inflearnhodolman.request.PostSearch;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.hibernate.event.spi.PostCollectionRecreateEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


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

    @Test
    void 게시글_전체조회_테스트(){
        final var list = IntStream.range(1,31).mapToObj(i -> Posts.newPost()
                .title("게시글 " + i)
                .content("내용 "+ i)
                .generate()).toList();
        postRepository.saveAll(list);
        final var postSearch = new PostSearch(1, 10);
        Assertions.assertThat(postService.getAll(postSearch).size()).isEqualTo(10);
    }

}