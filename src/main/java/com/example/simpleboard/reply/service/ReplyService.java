package com.example.simpleboard.reply.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyRequest;
import com.example.simpleboard.reply.model.ReplyViewRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create( ReplyRequest replyRequest){
        var optionalPostEntity = postRepository.findById(replyRequest.getPostId()); //바로 get말고 예외

        if(optionalPostEntity.isEmpty()){
            throw new RuntimeException("게시글이 존재하지 않음" + replyRequest.getPostId());
        }
        var entity =  ReplyEntity.builder()
                .post(optionalPostEntity.get())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();
       return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }
    public List<ReplyEntity> all(){
        return replyRepository.findAll();
    }
    public void delete(ReplyViewRequest replyViewRequest){
        replyRepository.findById(replyViewRequest.getPostId())
                .map( it ->{
                    if( !it.getPassword().equals(replyViewRequest.getPassword())){
                        var format =" 비밀번호 오류 %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(),replyViewRequest.getPassword()));
                    }
                    it.setStatus("UNREGISTERED");
                    replyRepository.save(it);
                    return it;
                }).orElseThrow(
                        () ->{
                            return new RuntimeException("비밀번호가 일치하지 않는다. ");
                        }
                );
    }
}
