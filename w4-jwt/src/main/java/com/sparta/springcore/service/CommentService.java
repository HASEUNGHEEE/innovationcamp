package com.sparta.springcore.service;

import com.sparta.springcore.model.Post;
import com.sparta.springcore.repository.PostRepository;
import com.sparta.springcore.dto.CommentRequestDto;
import com.sparta.springcore.dto.CommentResponseDto;
import com.sparta.springcore.model.Comment;
import com.sparta.springcore.model.Member;
import com.sparta.springcore.repository.CommentRepository;
import com.sparta.springcore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service

public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 댓글 등록
    @Transactional
    public void saveNewComments(Long postid, String memberProxy, CommentRequestDto requestDtoList) {
        Member member = memberRepository.findByUsername(memberProxy)
                .orElseThrow(RuntimeException::new);
        Post post = postRepository.findById(postid)
                .orElseThrow(RuntimeException::new);

        Comment comment = requestDtoList.toEntity();
        comment.registCommentInfo(post, member);
        commentRepository.save(comment);
    }


    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsByPostId(Long postid) {
        List<Comment> commentListByPostId =  commentRepository.findAllByPostId(postid);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for(Comment comment : commentListByPostId){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }

    // 댓글 삭제
    public Boolean deleteComment(Long id, String member){
        // comment내의 memberid와 로그인한 member아이디 일치하는지 확인
        Comment commentByCommentId =  commentRepository.findById(id).get();
        if (!Objects.equals(member, commentByCommentId.getMember().getUsername())) {
            return false;
            //throw new RuntimeException("댓글을 삭제하려는 유저의 아이디가 작성자의 아이디와 일치하지 않습니다.");
        }else {commentRepository.deleteById(id);}
        return true;
    }

    // 댓글 수정
    public Boolean updateComment(Long id, CommentRequestDto requestDto, String member) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        // comment내의 memberid와 로그인한 member아이디 일치하는지 확인
        Comment commentByCommentId = commentRepository.findById(id).get();

        if (!Objects.equals(member, comment.getMember().getUsername())){
            return false;
        } else {
            comment.updateComment(requestDto);
            commentRepository.save(comment);
        }
        return true;
    }
}
