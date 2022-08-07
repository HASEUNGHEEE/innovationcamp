//package com.sparta.springweb.controller;
//
//
//import com.sparta.springweb.dto.ContentsRequestDto;
//import com.sparta.springweb.dto.ContentsResponseDto;
//import com.sparta.springweb.dto.PostSavedResponseDto;
//import com.sparta.springweb.model.Contents;
//import com.sparta.springweb.repository.ContentsRepository;
//import com.sparta.springweb.security.UserDetailsImpl;
//import com.sparta.springweb.service.ContentsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//public class ContentsController {
//
//    private final ContentsRepository ContentsRepository;
//    private final ContentsService ContentsService;
//
//    // 게시글 조회
//    @GetMapping("/api/contents")
//    public List<ContentsResponseDto> getContents() {
//        return ContentsService.getContents();
//    }
//
//    // 게시글 디테일 조회
//    @GetMapping("/api/contents/{id}")
//    public Contents getContents(@PathVariable Long id) {
//        Contents contents =  ContentsRepository.findById(id).orElseThrow(
//                ()->new IllegalArgumentException("id가 존재하지 않습니다."));
//        return contents;
//    }
//
//
//    @PostMapping(value = "/api/contents", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<PostSavedResponseDto> createContents(@RequestPart @Valid ContentsRequestDto contentsRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart MultipartFile imageFile) {
//        String username = userDetails.getUser().getUsername();
//        Contents savedPost = ContentsService.createContents(contentsRequestDto, username, imageFile);
//        return ResponseEntity.ok().body(new PostSavedResponseDto(savedPost.getTitle(), savedPost.getContents(), savedPost.getName(), savedPost.getFilePath(), savedPost.getCreatedAt()));
//    }
//
//}

package com.sparta.springweb.controller;


import com.sparta.springweb.dto.ContentsRequestDto;
import com.sparta.springweb.dto.ContentsResponseDto;
import com.sparta.springweb.model.Contents;
import com.sparta.springweb.repository.ContentsRepository;
import com.sparta.springweb.security.UserDetailsImpl;
import com.sparta.springweb.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsController {

    private final ContentsRepository ContentsRepository;
    private final ContentsService ContentsService;

    // 게시글 조회
    @GetMapping("/api/contents")
    public List<ContentsResponseDto> getContents() {
        return ContentsService.getContents();
    }



    // 게시글 디테일 조회 +countReply 카운팅되게 해야 함
    @GetMapping("/api/contents/{id}")
    public Contents getDetailContents(@PathVariable Long id) {
        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("id가 존재하지 않습니다."));
        return contents;
    }

    // 게시글 작성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID의 username
        String username = userDetails.getUser().getUsername();
        Contents contents = ContentsService.createContents(requestDto, username);
        return contents;
    }

    //게시글 좋아요
    @PostMapping("/api/contents/{id}/likes")
    public void likeContents(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        ContentsService.likeContents(id, username);
    }
}