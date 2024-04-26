package com.example.simpleboard.post.controller;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor //final 사용시
public class PostApiController {
    private final PostService postService;

    @PostMapping("") //게시글 생성
    public PostEntity create(@Valid @RequestBody PostRequest postRequest){
        return postService.create(postRequest);
    }

    @PostMapping("/view") //아니 view인데 왜 post
    public PostEntity view(@Valid @RequestBody PostViewRequest postViewRequest){ //게시글 보기
        var entity = postService.view(postViewRequest);
        return entity;
    }

    @GetMapping("/all")
    public List<PostEntity> list(){ //list를 뿌려야 하기 때문에
        return postService.all();
    }

   @PostMapping("/delete")
    public void delete(@Valid @RequestBody PostViewRequest postViewRequest){ //게시글 삭제 pw를 넣어야 삭제되기 때문에@PathVariable Long id은 불가능
                            //viewRequest 를 같이 쓴다.
         postService.delete(postViewRequest);
    }
}
