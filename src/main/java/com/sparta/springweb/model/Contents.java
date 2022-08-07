package com.sparta.springweb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.springweb.dto.ContentsRequestDto;
import com.sparta.springweb.dto.ContentsLikeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Contents extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contents;

    private String filePath;

    private int countReply;


    @Column
    private Long likeCnt;

    @JsonIgnore
    @ManyToMany
    private List<ContentsLikeDto> likeUsers = new ArrayList<>();


    public Contents(String title, String username, String contents) {
        this.title = title;
        this.name = username;
        this.contents = contents;
    }

    public Contents(ContentsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

    public Contents(ContentsRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.name = username;
        this.contents = requestDto.getContents();
        this.likeCnt = 0L;
        this.countReply = getCountReply();
    }

    public void update(ContentsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

    public Contents(ContentsRequestDto requestDto, String username,String filePath) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.name = username;
        this.filePath = filePath;
    }
}
