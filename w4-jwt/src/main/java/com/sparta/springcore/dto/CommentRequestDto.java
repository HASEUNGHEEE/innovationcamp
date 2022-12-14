package com.sparta.springcore.dto;

import com.sparta.springcore.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CommentRequestDto {

    private String contents;

    public Comment toEntity () {
        return Comment.builder()
                .contents(contents)
                .build();
    }
}
