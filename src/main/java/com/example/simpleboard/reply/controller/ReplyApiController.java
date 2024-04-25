package com.example.simpleboard.reply.controller;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyRequest;
import com.example.simpleboard.reply.model.ReplyViewRequest;
import com.example.simpleboard.reply.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("")
    public ReplyEntity create(@Valid @RequestBody ReplyRequest replyRequest){
        return  replyService.create(replyRequest);
    }
    @GetMapping("/all")
    public List<ReplyEntity> list(){
        return replyService.all();
    }
    @PostMapping("/delete")
    public void delete(@Valid @RequestBody ReplyViewRequest replyViewRequest){
        replyService.delete(replyViewRequest);
    }
}
