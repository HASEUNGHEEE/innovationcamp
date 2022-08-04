package com.sparta.springcore.service;

import com.sparta.springcore.dto.PostDetailResponseDto;
import com.sparta.springcore.dto.PostRequestDto;
import com.sparta.springcore.dto.PostResponseDto;
import com.sparta.springcore.model.Member;
import com.sparta.springcore.model.Post;
import com.sparta.springcore.repository.MemberRepository;
import com.sparta.springcore.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    // 게시글 등록
    @Transactional
    public PostDetailResponseDto registerPost(PostRequestDto requestDto, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 Id의 회원이 존재하지 않습니다.")
        );

        // 게시글 작성자 저장 (편의 메서드 -> member에도 posts에 해당 post add)
        Post post = new Post(requestDto, member);
        postRepository.save(post);

        // 저장된 Post -> PostDetailResponseDto에 담아 리턴
        return new PostDetailResponseDto(post);
    }

    // 게시글 전체 조회, 검색 조회, 카테고리 조회
    public List<PostResponseDto> getAllPost() {
        List<Post> postList = null;
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for(Post post : postList){
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);
        }

        return postResponseDtoList;
    }

    // 게시글 상세 조회
    public PostDetailResponseDto getPostDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        return new PostDetailResponseDto(post);
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long id, PostRequestDto requestDto, String username) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        if (!Objects.equals(username, post.getMember().getUsername())){
            throw new IllegalArgumentException("본인의 게시글만 수정할 수 있습니다.");
        }

        post.updatePost(requestDto);
        postRepository.save(post);
    }

    // 게시글 삭제
    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        if (!Objects.equals(username, post.getMember().getUsername())){
            throw new IllegalArgumentException("본인의 게시글만 수정할 수 있습니다.");
        }

        postRepository.deleteById(id);
    }



}