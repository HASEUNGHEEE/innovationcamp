package com.sparta.springweb.service;

import com.sparta.springweb.dto.CommentLikeDto;
import com.sparta.springweb.dto.CommentRequestDto;
import com.sparta.springweb.model.Comment;
import com.sparta.springweb.model.User;
import com.sparta.springweb.repository.CommentLikeRepository;
import com.sparta.springweb.repository.CommentRepository;
import com.sparta.springweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private final CommentLikeRepository commentLikeRepository;

    // 댓글 조회
    public List<Comment> getComment(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreatedAtDesc(postId);
    }

    // 댓글 작성
    @Transactional
    public void createComment(Long postId, CommentRequestDto requestDto,String username) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Comment comment = new Comment(postId, requestDto, username);
        commentRepository.save(comment);
    }

    // 댓글 수정
    @Transactional
    public String update(Long id, CommentRequestDto requestDto, String username) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다."));
        String writername = comment.getUsername();
        if (Objects.equals(writername, username)) {
            comment.update(requestDto);
            return "댓글 수정 완료";
        } return "작성한 유저가 아닙니다.";
    }

    // 댓글 삭제
    public String deleteComment(Long id, String username) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다."));
        String writername = comment.getUsername();
        if (Objects.equals(writername, username)) {
            commentRepository.deleteById(id);
            return "댓글 삭제 완료";
        }
        return "작성한 유저가 아닙니다.";
    }

    // 댓글 좋아요
    @Transactional
    public void likeComment(Long postId, Long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 Id의 회원이 존재하지 않습니다.")
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        List<CommentLikeDto> likeUsers = comment.getLikeUsers();
        Object[] objects = likeUsers.stream()
                .filter(m -> m.getUsername().equals(username) && Objects.equals(m.getCommentId(), comment.getId())).toArray();
        if(objects.length == 0){
            CommentLikeDto commentLikeDto = new CommentLikeDto(username, comment.getId());
            commentLikeRepository.save(commentLikeDto);
            comment.getLikeUsers().add(commentLikeDto);
            comment.setLikeCnt(comment.getLikeCnt() + 1);
        }else{
            CommentLikeDto commentLikeDto = commentLikeRepository.findByUsernameAndCommentId(username, comment.getId());
            comment.getLikeUsers().remove(commentLikeDto);
            comment.setLikeCnt(comment.getLikeCnt() - 1);
        }
    }
}