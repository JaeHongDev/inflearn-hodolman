package com.jaehonglog.inflearnhodolman.controller;


import com.jaehonglog.inflearnhodolman.request.PostCreate;
import com.jaehonglog.inflearnhodolman.service.PostService;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    @PostMapping("/posts")
    public Map<String, String>get(@RequestBody @Valid  PostCreate request){
        postService.write(request);
        return Map.of();
    }
}
