package com.example.simpleboard.post.controller;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor //final 사용시
public class PostApiController {
    private final PostService postService;

    public PostEntity create(@Valid PostRequest postRequest){
        return postService.create(postRequest);
    }
}
