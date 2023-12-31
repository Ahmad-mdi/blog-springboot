package com.blog.service;

import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post save(Post post) throws Exception {
        try {
            if (post.getTitle().isEmpty() || post.getImage().isEmpty() || post.getBody().isEmpty())
                throw new Exception("please complete filed!");
            return repository.save(post);
        } catch (DataIntegrityViolationException e) {
            throw new Exception("the title has already been taken!", e);
        }
    }

    public Post findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Post findById(long id) {
        Optional<Post> data = repository.findById(id);
        return data.orElse(null);
    }

    public Post edit(Post post) throws Exception {
        try {
            if (post.getTitle().isEmpty() || post.getImage().isEmpty() || post.getBody().isEmpty())
                throw new Exception("please complete filed!");
            Post oldPost = repository.findById(post.getId()).orElse(null);
            assert oldPost != null;
            oldPost.setTitle(post.getTitle());
            oldPost.setImage(post.getImage());
            oldPost.setBody(post.getBody());
            return repository.save(oldPost);
        } catch (DataIntegrityViolationException e) {
            throw new Exception("the title has already been taken!", e);
        }
    }
}
