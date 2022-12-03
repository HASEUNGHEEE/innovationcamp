package com.sparta.springcore.model;

import com.sparta.springcore.dto.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String contents;


    // FK로 MEMBER_ID 들어옴.
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // FK로 POST_ID 들어옴.
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;



    @Builder
    public Comment(String contents, Post post, Member member) {
        this.contents = contents;
    }

    public void registCommentInfo(Post post, Member member) {
        this.post = post;
        this.member = member;
    }
    public void updateComment(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}

