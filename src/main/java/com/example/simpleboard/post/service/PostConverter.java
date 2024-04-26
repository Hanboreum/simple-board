package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostDto;
import com.example.simpleboard.post.db.PostEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//데이터 변환
@Service
public class PostConverter {

    // entity -> dto로 변환
    public PostDto toDto(PostEntity postEntity){
        return  PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .email(postEntity.getEmail())
                .password(postEntity.getPassword())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .build();
    }
}
//entity를 뷰까지 내리는 일은 없어야 한다. 그렇기 위해서 entity와 상응하는 dto를 만들어야 함
//converter 를 통해 데이터 변환.