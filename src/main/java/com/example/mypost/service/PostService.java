package com.example.mypost.service;

import com.example.mypost.dto.PostRequestDto;
import com.example.mypost.entity.Post;
import com.example.mypost.repository.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")

        );
        post.update(requestDto);
        return post.getId();
    }
}
