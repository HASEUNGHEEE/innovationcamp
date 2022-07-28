package com.example.mypost.web;

import com.example.mypost.domain.LoginForm;
import com.example.mypost.domain.LoginService;
import com.example.mypost.domain.Post;
import com.example.mypost.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;
    private final LoginService loginService;


    @GetMapping
    public String posts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "basic/posts";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "basic/post";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("post", new Post());
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addPost(Post post, RedirectAttributes redirectAttributes) {
        Post savedPost = postRepository.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/posts";
    }
    @GetMapping("/{postId}/edit-login")
    public String loginForm(@ModelAttribute("loginform") LoginForm form){
        return "/basic/loginForm";
    }

    @PostMapping("/{postId}/edit-login")
    public String login(@ModelAttribute LoginForm form,
                        @RequestParam(defaultValue = "/basic/posts/{postId}/edit") String redirectURL) {

        Post loginMember = loginService.login(form.getPassword());

        if (loginMember == null) {
            return "redirect:/basic/posts/";
        }

        return "redirect:" + redirectURL;

    }

    @GetMapping("/{postId}/delete-login")
    public String dlloginForm(@ModelAttribute("loginform") LoginForm form){
        return "/basic/loginForm";
    }

    @PostMapping("/{postId}/delete-login")
    public String dllogin(@ModelAttribute LoginForm form,
                          @RequestParam(defaultValue = "/basic/posts/{postId}/delete") String redirectURL) {


        Post loginMember = loginService.login(form.getPassword());

        if (loginMember == null) {
            return "redirect:/basic/items/";
        }

        return "redirect:" + redirectURL;

    }


    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "basic/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @ModelAttribute Post post) {
        postRepository.update(postId, post);
        return "redirect:/basic/posts";
    }

    @GetMapping ("/{postId}/delete")
    public String delete(@PathVariable long postId) {
        postRepository.deleteById(postId);
        return "redirect:/basic/posts";
    }




    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        postRepository.save(new Post("시루랑판다", "코드쿤스트", "너무귀여워", "1234"));
        postRepository.save(new Post("+코쿤도", "아리울", "너무귀여워","1221"));
    }

}
