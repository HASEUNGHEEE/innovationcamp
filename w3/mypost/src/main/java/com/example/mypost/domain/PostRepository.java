package com.example.mypost.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private static final Map<Long, Post> store = new ConcurrentHashMap<>(); //static
    private static long sequence = 0L; //static

    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;

    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public Optional<Post> findByPw(String password) {
        return findAll().stream()
                .filter(m -> m.getPassword().equals(password))
                .findFirst();
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long postId, Post updateParam) {
        Post findPost = findById(postId);
        findPost.setContents(updateParam.getContents());
        findPost.setTitle(updateParam.getTitle());
        findPost.setUsername(updateParam.getUsername());
        findPost.setPassword(updateParam.getPassword());
    }
    public void deleteById(long itemId) {
        store.remove(itemId);

    }


    public void clearStore() {
        store.clear();
    }

}
