package com.sparta.springweb.service;

import com.sparta.springweb.dto.ContentsRequestDto;
import com.sparta.springweb.dto.ContentsResponseDto;
import com.sparta.springweb.dto.ContentsLikeDto;
import com.sparta.springweb.model.Contents;
import com.sparta.springweb.model.User;
import com.sparta.springweb.repository.CommentRepository;
import com.sparta.springweb.repository.ContentsRepository;
import com.sparta.springweb.repository.ContentsLikeRepository;
import com.sparta.springweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ContentsService {

    private final ContentsRepository ContentsRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private final ContentsLikeRepository contentsLikeRepository;
//    private final StorageService storageService;

    //테스트용 주석 처리
//    @Transactional
//    public Contents createContents(ContentsRequestDto contentsRequestDto, String username, MultipartFile imageFile) {
//        String filePath = storageService.uploadFile(imageFile);
//        return ContentsRepository.save(new Contents(contentsRequestDto, username, filePath));
//    }


    @Transactional
    public Contents createContents(ContentsRequestDto contentsRequestDto, String username) {
        return ContentsRepository.save(new Contents(contentsRequestDto, username));
    }

    // 게시글 조회
    public List<ContentsResponseDto> getContents() {
        List<Contents> contents = ContentsRepository.findAllByOrderByCreatedAtDesc();
        List<ContentsResponseDto> listContents = new ArrayList<>();
        for (Contents content : contents) {
            // + 댓글 개수 카운팅 (추가 기능)
            int countReply = commentRepository.countByPostId(content.getId());
            ContentsResponseDto contentsResponseDto = ContentsResponseDto.builder()
                    .content(content)
                    .countReply(countReply)
                    .build();
            listContents.add(contentsResponseDto);
        }
        return listContents;
    }



    // 게시글 좋아요
    @Transactional
    public void likeContents(Long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 Id의 회원이 존재하지 않습니다.")
        );

        Contents contents = ContentsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        List<ContentsLikeDto> likeUsers = contents.getLikeUsers();
        Object[] objects = likeUsers.stream()
                .filter(m -> m.getUsername().equals(username) && Objects.equals(m.getContentsId(), contents.getId())).toArray();
        if(objects.length == 0){
            ContentsLikeDto ContentsLikeDto = new ContentsLikeDto(username, contents.getId());
            contentsLikeRepository.save(ContentsLikeDto);
            contents.getLikeUsers().add(ContentsLikeDto);
            contents.setLikeCnt(contents.getLikeCnt() + 1);
        }else{
            ContentsLikeDto ContentsLikeDto = contentsLikeRepository.findByUsernameAndContentsId(username, contents.getId());
            contents.getLikeUsers().remove(ContentsLikeDto);
            contents.setLikeCnt(contents.getLikeCnt() - 1);
        }

    }

    // 게시글 수정 기능 (사용 안함)
    @Transactional
    public Long update(Long id, ContentsRequestDto requestDto) {
        Contents Contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Contents.update(requestDto);
        return Contents.getId();
    }

    // 게시글 삭제
    public void deleteContent(Long ContentId, String userName) {
        String writer = ContentsRepository.findById(ContentId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")).getName();
        if (Objects.equals(writer, userName)) {
            ContentsRepository.deleteById(ContentId);
        }else new IllegalArgumentException("작성한 유저가 아닙니다.");
    }
}