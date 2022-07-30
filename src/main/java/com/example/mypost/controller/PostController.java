package com.example.mypost.controller;

import com.example.mypost.dto.PostRequestDto;
import com.example.mypost.entity.Post;
import com.example.mypost.repository.PostRepository;
import com.example.mypost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/basic/posts")
@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

//  private final PostService postService;

    //게시글 전체 조회페이지
    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> post = postRepository.findAllByOrderByModifiedAtDesc();
        model.addAttribute("post", post);
        return "basic/posts";
    }

    //게시글 작성 페이지
    @GetMapping("/add")
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "basic/addPost";
    }
    //게시글 작성
    @PostMapping("/add")
    public String createPost(@ModelAttribute PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return "redirect:/basic/posts";
    }

//    //게시글 작성
//    @PostMapping("/add")
//    public String createPost(Post post, RedirectAttributes redirectAttributes) {
//        Post savedPost = postRepository.save(post);
//        redirectAttributes.addAttribute("postId", savedPost.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/basic/posts";
//    }


    //게시글 상세 조회페이지
    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        model.addAttribute("post", post);
        return "basic/detailPost";
    }

    @GetMapping("/api/post/{id}/edit")
    public String getEditPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        model.addAttribute("post", post);
        return "redirect:/editPost";
    }


    @PutMapping("/api/post/{id}/edit")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return "redirect:/";

    }

    @DeleteMapping("/api/post/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/posts";
    }
}


