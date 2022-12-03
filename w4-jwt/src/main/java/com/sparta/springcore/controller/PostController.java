package com.sparta.springcore.controller;

import com.sparta.springcore.dto.PostDetailResponseDto;
import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.dto.PostRequestDto4Put;
import com.sparta.springcore.dto.PostResponseDto;
import com.sparta.springcore.security.SecurityUtil;
import com.sparta.springcore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/api/posts")
    public PostDetailResponseDto registerPost(@RequestBody PostRequestDto requestDto,
                                              @AuthenticationPrincipal User userDetails){
        // 서버 측 세션 저장소에 저장된 유저 정보는 아래와 같은 방식으로 가져온다.
        // 1. @AuthenticationPrincipal 어노테이션을 통해 저장된 principal 유형에 맞춰 파라미터에서
        // 2. 위 어노테이션이 해주는 일을 직접 풀어서 사용 하는 법 -> SecurityContextHolder.getContext().getAuthentication();
        // 3. 아래와 같이 2번 방식으로 유저 정보를 불러오는 기능을 모듈화하여 사용
        String username = SecurityUtil.getCurrentMemberUsername();

        return postService.registerPost(requestDto, username);
    }


    // 게시글 전체 조회, 검색 조회, 카테고리 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getAllPost(){
        return postService.getAllPost();
    }

    // 게시글 상세 조회
    @GetMapping("/api/posts/{id}")
    public PostDetailResponseDto getPostDetail(@PathVariable Long id){
        return postService.getPostDetail(id);
    }

    // 게시글 수정
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/api/posts/{id}")
    public void updatePost(@PathVariable Long id,
                           @RequestBody PostRequestDto requestDto
    ){
        String username = SecurityUtil.getCurrentMemberUsername();
        postService.updatePost(id, requestDto, username);
    }

    //게시글 삭제
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id){
        String username = SecurityUtil.getCurrentMemberUsername();
        postService.deletePost(id, username);
    }

}
