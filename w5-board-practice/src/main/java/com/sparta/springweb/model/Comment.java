package com.sparta.springweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.springweb.dto.CommentLikeDto;
import com.sparta.springweb.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@Setter
public class Comment extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column
    private Long likeCnt;

    @JsonIgnore
    @ManyToMany
    private List<CommentLikeDto> likeUsers = new ArrayList<>();

    //@OneToMany 대댓글


    public Comment(Long postId, CommentRequestDto requestDto, String username) {
        this.comment = requestDto.getComment();
        this.username = username;
        this.postId = postId;
        this.likeCnt = 0L;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}

