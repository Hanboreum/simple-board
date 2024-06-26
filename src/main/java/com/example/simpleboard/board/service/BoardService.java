package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDTO;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BoardService {
    //repository와 연결
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDTO create(BoardRequest boardRequest){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var saveEntity =  boardRepository.save(entity);
        return boardConverter.toDto(saveEntity);
    }

    public BoardDTO view(Long id) { //Boardentity 는 더이상 뷰로 내려가지 않는다
        var entity = boardRepository.findById(id).get();
        return  boardConverter.toDto(entity);
    }
}
