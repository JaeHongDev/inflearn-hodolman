package com.jaehonglog.inflearnhodolman.controller;


import com.jaehonglog.inflearnhodolman.entity.Posts;
import com.jaehonglog.inflearnhodolman.request.PostCreate;
import com.jaehonglog.inflearnhodolman.response.PostResponse;
import com.jaehonglog.inflearnhodolman.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    @PostMapping("/posts")
    public void save(@RequestBody @Valid  PostCreate request){
        postService.write(request);
    }

    @GetMapping("/posts/{id}")
    public PostResponse get(@PathVariable("id") Long id){
        return postService.get(id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getAll(){
        return postService.getAll();
    }
}
