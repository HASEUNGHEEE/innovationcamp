package com.sparta.springweb.repository;

import com.sparta.springweb.dto.ContentsLikeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsLikeRepository extends JpaRepository<ContentsLikeDto, Long> {
    ContentsLikeDto findByUsernameAndContentsId(String username, Long contentsId);
}
