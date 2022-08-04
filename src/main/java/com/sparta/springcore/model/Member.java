package com.sparta.springcore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Member extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value =  EnumType.STRING)
    private UserRole userRole;


    @JsonIgnore
    @Column
    // 자바 객체 사이드에서만 저장됨 (DB에 저장안됨)
    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @JsonIgnore
    // 자바 객체 사이드에서만 저장됨 (DB에 저장안됨)
    @OneToMany(mappedBy = "member")
    private List<Comment> comments;



    public Member(String username, String nickname, String password, UserRole userRole) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.userRole = userRole;
    }

    @Builder
    public Member(String username, String password, UserRole userRole, String nickname) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.nickname = nickname;
    }
}