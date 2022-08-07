//package com.sparta.springweb.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Builder;
//import lombok.Getter;
//
//import javax.validation.constraints.NotBlank;
//import java.time.LocalDateTime;
//
//@Getter
//public class PostSavedResponseDto {
//    @NotBlank(message = "제목을 입력해주세요.")
//    private final String title;
//    @NotBlank(message = "내용을 입력해주세요.")
//    private final String contents;
//    private final String writer;
//
//    //테스트용 주석처리
////    private final String filePath;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private final LocalDateTime createdAt;
//
//    //테스트용 주석 처리
////    @Builder
////    public PostSavedResponseDto(String title, String contents, String username, String filePath, LocalDateTime createdAt) {
////        this.title = title;
////        this.contents = contents;
////        this.writer = username;
////        this.filePath = filePath;
////        this.createdAt = createdAt;
////    }
//
//    @Builder
//    public PostSavedResponseDto(String title, String contents, String username, LocalDateTime createdAt) {
//        this.title = title;
//        this.contents = contents;
//        this.writer = username;
//        this.createdAt = createdAt;
//    }
//}
