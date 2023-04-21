package com.jaehonglog.inflearnhodolman.service;


import com.jaehonglog.inflearnhodolman.entity.Posts;
import com.jaehonglog.inflearnhodolman.repository.PostRepository;
import com.jaehonglog.inflearnhodolman.request.PostCreate;
import com.jaehonglog.inflearnhodolman.request.PostSearch;
import com.jaehonglog.inflearnhodolman.response.PostResponse;
import jakarta.transaction.TransactionScoped;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @Transactional
    public void write(PostCreate postCreate){
        final var posts = Posts.newPost()
                .content(postCreate.getContent())
                .title(postCreate.getTitle())
                .generate();
        postRepository.save(posts);
    }

    @Transactional
    public PostResponse get(Long id){
        final var post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 id입니다."));
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .build();
    }

    public List<PostResponse> getAll(PostSearch postSearch) {
        return this.postRepository.getList(postSearch)
                .stream().map(PostResponse::of)
                .toList();
    }
}
