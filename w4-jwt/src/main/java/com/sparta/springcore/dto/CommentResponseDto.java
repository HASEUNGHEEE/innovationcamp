package com.sparta.springcore.dto;

import com.sparta.springcore.model.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CommentResponseDto {

    private Long id;
    private String username;
    private String contents;
    private String nickname;
    private LocalDateTime date;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getMember().getUsername();
        this.contents = comment.getContents();
        this.nickname = comment.getMember().getNickname();
        this.date = comment.getModifiedAt();
    }
}