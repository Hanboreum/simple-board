package com.example.simpleboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    //Query Method 형식
    // select * from post where id = [] and status order by id desc limit = 1;
    //삭제된 글은 조회되지 않게 패치
     Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);
}
