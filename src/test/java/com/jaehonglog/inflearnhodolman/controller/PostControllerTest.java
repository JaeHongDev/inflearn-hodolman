package com.jaehonglog.inflearnhodolman.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jaehonglog.inflearnhodolman.repository.PostRepository;
import com.jaehonglog.inflearnhodolman.request.PostCreate;
import com.jaehonglog.inflearnhodolman.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void tearDown(){
        this.postRepository.deleteAll();
    }


    @Test
    @DisplayName("/posts 요청시 hello가 응답됩니다")
    void test_hello() throws Exception {
        final var body = """
              {
                    "title":"제목",
                    "content":"내용입니다."
               }
                """;
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception {
        final var body = """
                {
                    "title":"",
                    "content":"내용입니다."
                }
                """;
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("제목이 필요합니다."));
    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test3() throws Exception {
        final var body = """
                {
                    "title":"제목입니다.",
                    "content":"내용입니다."
                }
                """;
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    void test4() throws Exception{

        var title = "제목입니다.";
        var content = "내용입니다.";
        this.createPost(title, content);

        final var id = postRepository.findAll().get(0).getId();

        mockMvc.perform(get("/posts/"+id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpectAll(
                            jsonPath("$.content").value(content),
                            jsonPath("$.title").value(title)
                );
    }

    private void createPost(String title, String content) {
        postService.write(PostCreate.builder()
                .title(title)
                .content(content).build());
    }

    @Test
    void test5() throws Exception{
        this.createPost("1234567890123456","content");

        final var id = postRepository.findAll().get(0).getId();
        mockMvc.perform(get("/posts/"+ id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content").value("content"),
                        jsonPath("$.title").value("1234567890")
                );
    }


}