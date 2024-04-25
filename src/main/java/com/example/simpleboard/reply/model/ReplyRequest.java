package com.example.simpleboard.reply.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyRequest {
    @NotNull
    private Long postId; //게시글에 대한 답변
    @NotBlank
    private String userName; //직원 or 사용자 이름
    @NotBlank
    @Size(min = 4, max = 4)
    private String password; //직원이 작성하는 것이기 때문에 필요 없을 수도
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
