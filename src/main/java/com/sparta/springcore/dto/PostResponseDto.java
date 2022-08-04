package com.sparta.springcore.dto;

import com.sparta.springcore.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;

    private String username;

    private String title;

    private String contents;

    private String nickname;



    public PostResponseDto(Post post){
        this.id = post.getId();
        this.username = post.getMember().getUsername();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.nickname = post.getNickname();
    }

}
