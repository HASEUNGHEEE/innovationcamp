package com.sparta.springcore.dto;

import com.sparta.springcore.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostDetailResponseDto {
    private Long id;

    private String title;

    private String contents;

    private String nickname;

    private LocalDateTime date;


    public PostDetailResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.nickname = post.getNickname();
        this.date = post.getModifiedAt();
    }
}
