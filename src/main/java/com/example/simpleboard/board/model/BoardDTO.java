package com.example.simpleboard.board.model;

import com.example.simpleboard.post.db.PostDto;
import com.example.simpleboard.post.db.PostEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardDTO {

    private Long id;
    private String boardName;
    private String status;
    private List<PostDto> postList = List.of();

}
//dto 존재 이유 : 무한 루프 제거