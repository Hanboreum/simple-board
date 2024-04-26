package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {

    @Id //pk, ai
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;
    private String status;

    //1: N 관계
    //entity안에 변수가 있으면 컬럼으로 보기 떄문에
    //mappedBy 를 했기에 boardConverter에서 연결된 n개의 리스트를 가져오게됨
    @OneToMany (mappedBy = "board") //board 가 postentity 안에 있어야 함
   // @Where(clause = "status ='Registerd' ") //조건절. postentity 클래스의 status = Registerd, 즉 삭제되지 않은 것만 가져온다.
    @Builder.Default
    private List<PostEntity> postList = List.of();
}
