package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne //나 N, 상대방(boardentity) 1, 시스템상 board_id
    @JsonIgnore //무한반복 제거
    @ToString.Exclude //무한 반복 제거
    private BoardEntity board; //1 : N 연결
    //구: post_id
    private String userName;
    private String password;
    private String email;
    private String status; //BoardEntity@Where(clause = "status =Registerd")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;
    //@Transient// db column으로 사용하지 않겠다
    @OneToMany(mappedBy = "post") //post = 1. post에 매핑
    private List<ReplyEntity> replyList = List.of(); //빈 arraylist를 defaultfh
}
