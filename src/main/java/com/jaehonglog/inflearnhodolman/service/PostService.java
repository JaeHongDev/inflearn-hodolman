package com.jaehonglog.inflearnhodolman.service;


import com.jaehonglog.inflearnhodolman.entity.Posts;
import com.jaehonglog.inflearnhodolman.repository.PostRepository;
import com.jaehonglog.inflearnhodolman.request.PostCreate;
import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional()
    public void write(PostCreate postCreate){
        final var posts = Posts.newPost()
                .content(postCreate.getContent())
                .title(postCreate.getTitle())
                .generate();
        postRepository.save(posts);
    }
}
