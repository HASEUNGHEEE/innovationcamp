package com.sparta.springcore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String nickname;

    // FK로 memberId 들어옴.
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


    // postRequestDto 받는 생성자
    public Post(PostRequestDto requestDto, Member member){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.nickname = member.getNickname();
        this.member = member;
    }

    public void setMember(UserDetailsImpl userDetails){
        this.member = userDetails.getUser();
    }

    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

}
