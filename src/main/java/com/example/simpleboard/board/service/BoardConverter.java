package com.example.simpleboard.board.service;
//dto를 변환 시켜줌.
//converter =데이터 변환 (build()코드를 컨버터가)

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDTO;
import com.example.simpleboard.post.db.PostDto;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;
    //entity를 dto로 변환시켜준다
    public BoardDTO toDto (BoardEntity boardEntity){

        var postList = boardEntity.getPostList()
                .stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        return BoardDTO.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                //mappedBy 를 했기에 boardConverter에서 연결된 n개의 리스트를 가져오게됨
                //postConverter로 만들어야
                .postList(postList)
                .build();
    }
}

/*

 boardEntity.getPostList().stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

                var postList = boardEntity.getPostList().stream()
                        .map(postEntity -> {
                           return postConverter.toDto(postEntity);
                        }).collect(Collectors.toList());
 */