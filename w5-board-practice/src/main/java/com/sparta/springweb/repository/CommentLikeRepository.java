package com.sparta.springweb.repository;

import com.sparta.springweb.dto.CommentLikeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLikeDto, Long> {
    CommentLikeDto findByUsernameAndCommentId(String username, Long commentId);
}
