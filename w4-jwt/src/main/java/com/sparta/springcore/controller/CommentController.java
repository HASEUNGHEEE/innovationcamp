package com.sparta.springcore.controller;

import com.sparta.springcore.dto.CommentRequestDto;
import com.sparta.springcore.dto.CommentResponseDto;
import com.sparta.springcore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    // 댓글 생성
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/api/post/{postid}/comments")
    public ResponseEntity<Void> registComment (@PathVariable Long postid, @RequestBody CommentRequestDto requestDtoList) {

        //@AuthenticationPrincipal은 null로 받아온다. Authentication으로 받아오기.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();

        // 세션 가져오기...
        commentService.saveNewComments(postid, username, requestDtoList);
        return ResponseEntity.ok().build();
    }

    // 댓글 조회
    @GetMapping("/api/post/{postid}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId (@PathVariable Long postid) {
        return ResponseEntity.ok().body(commentService.getCommentsByPostId(postid));
    }

    // 댓글 삭제
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/api/post/{postid}/comments/{commentid}")
    public Boolean deleteComment(@PathVariable Long commentid){

        //@AuthenticationPrincipal은 null로 받아온다. Authentication으로 받아오기.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();

        //사용자의 id가 null값인지 검증
        Boolean result = false;

        if (username != null) {
            result = commentService.deleteComment(commentid, username);
        }

        return result;
    }
    // 댓글 수정
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/api/post/{postid}/comments/{commentid}")
    public Boolean updateComment(@PathVariable Long commentid, @RequestBody CommentRequestDto requestDto){

        //@AuthenticationPrincipal은 null로 받아온다. Authentication으로 받아오기.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();

        //사용자의 id가 null값인지 검증
        Boolean result = false;

        if (username != null) {
            result = commentService.updateComment(commentid, requestDto, username);
        }
        return result;
    }

}


