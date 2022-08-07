package com.sparta.springweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContentsLikeDto {
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;

    private Long contentsId;


    public ContentsLikeDto(String username, Long contentsId){
        this.username = username;
        this.contentsId = contentsId;
    }


}
