package com.example.simpleboard.reply.db;

import com.example.simpleboard.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne //reply = N
    @ToString.Exclude
    @JsonIgnore
    private PostEntity post; //post -> post_id
    private String userName;
    private String password;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime repliedAt;
}
