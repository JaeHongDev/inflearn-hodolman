package com.jaehonglog.inflearnhodolman.controller;


import com.jaehonglog.inflearnhodolman.request.PostCreate;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @PostMapping("/posts")
    public Map<String, String>get(@RequestBody @Valid  PostCreate params, BindingResult result){
        if(result.hasErrors()){
            final var errors = result.getFieldErrors();
            final var filedError = errors.get(0);

            final var fieldName = filedError.getField();
            final var fieldMessage = filedError.getDefaultMessage();
            return Map.of(fieldName, fieldMessage);
        }

        return Map.of();
    }
}
