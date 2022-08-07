package com.sparta.springweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.springweb.model.Contents;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContentsResponseDto {
    private Long id;
    private String title;
    private String name;
    private String contents;

    //테스트용 주석 처리
//    private String filePath;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    private int countReply;

    private Long likeCnt;

//    private List<UserLikeDto> likeUsers;

    //테스트용 주석 처리
//    @Builder
//    public ContentsResponseDto(Contents content, int countReply) {
//        this.id = content.getId();
//        this.title = content.getTitle();
//        this.name = content.getName();
//        this.filePath = content.getFilePath();
//        this.contents = content.getContents();
//        this.modifiedAt = content.getModifiedAt();
//        this.createdAt = content.getCreatedAt();
//        this.countReply = countReply;
//    }

    @Builder
    public ContentsResponseDto(Contents content, int countReply) {
        this.id = content.getId();
        this.title = content.getTitle();
        this.name = content.getName();
        this.contents = content.getContents();
        this.modifiedAt = content.getModifiedAt();
        this.createdAt = content.getCreatedAt();
        this.countReply = countReply;
        this.likeCnt = content.getLikeCnt();
//        this.likeUsers = content.getLikeUsers();
    }
}
