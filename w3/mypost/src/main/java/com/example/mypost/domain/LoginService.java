package com.example.mypost.domain;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final PostRepository postRepository;

    /**
     * @return null 로그인 실패
     */
    public Post login(String password) {
        return (Post) postRepository.findByPw(password)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }


}
