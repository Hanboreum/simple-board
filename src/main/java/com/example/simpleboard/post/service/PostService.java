package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardRequest;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostEntity create( PostRequest postRequest){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); // <- 임시고정
        var entity = PostEntity.builder()
                .board(boardEntity) //임시 고정
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("Registerd")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }
    /**
    1. 게시글의 유무
     2. 비밀번호 확인
     */

    public PostEntity view(PostViewRequest postViewRequest) {
       return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED") //repo에서 findbyid라는 옵셔널
               .map( it ->{ //map을 받게 된다.
                   if(!it.getPassword().equals(postViewRequest.getPassword())){ //해당 데이터가 있을 때만 비교한다
                       var format = "패스워드 오류 %s vs %s"; //패스워드 오류
                       throw new RuntimeException(String.format(format,it.getPassword(),postViewRequest.getPassword()));
                   }
                   //게시글을 찾아갈 때 답변 글도 같이 내려줌
                   //글이 이미 있다면
                   //postentity의 @onetomany의 mapped를 통해 자동으로 replylist를 가져온다
                   //var replyList = replyService.findAllByPostId(it.getId());
                   //it.setReplyList(replyList);

                   return it;//패스워드 같음
               }).orElseThrow( //데이터가 없다면
                       () ->{
                           return new RuntimeException("해당 게시글이 존재하지 않습니다. :" +postViewRequest.getPostId() );
                       }
               );
    }

    public List<PostEntity> all() {
        return postRepository.findAll();
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map( it ->{
                    //pw 존재
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        var format = "패스워드 오류 %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    //비밀번호가 맞다면
                    it.setStatus(("UNREGISTERED"));
                    postRepository.save(it);
                    return it; //map은 return 무조건
                }).orElseThrow(
                        () ->{
                            return new RuntimeException("비밀번호가 일치하지 않습니다" + postViewRequest.getPassword());
                        }
                );
    }
}
