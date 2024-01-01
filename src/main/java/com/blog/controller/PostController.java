package com.blog.controller;

import com.blog.entity.Post;
import com.blog.helper.enums.ResponseStatus;
import com.blog.helper.ServiceApiResponse;
import com.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
    private final PostService service;

    @GetMapping
    public ServiceApiResponse<Post> getAll(){
        try {
            List<Post> postList = service.findAll();
            return new ServiceApiResponse<>(postList, ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceApiResponse<>(e);
        }
    }

    @PostMapping("/save")
    public ServiceApiResponse<Post> create(@RequestBody @Valid Post post){
       try {
           Post data = service.save(post);
           return new ServiceApiResponse<>(data,ResponseStatus.SUCCESS);
       }catch (Exception e){
           return new ServiceApiResponse<>(e);
       }
    }

    @GetMapping("/showByTitle/{title}")
    public ServiceApiResponse<Post> getByTitle(@PathVariable String title){
        try {
            Post post = service.findByTitle(title);
            return new ServiceApiResponse<>(post,ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceApiResponse<>(e);
        }
    }

    @GetMapping("/showById/{id}")
    public ServiceApiResponse<Post> getById(@PathVariable long id){
        try {
            Post post = service.findById(id);
            return new ServiceApiResponse<>(post,ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceApiResponse<>(e);
        }
    }

    @PutMapping("/update")
    public ServiceApiResponse<Post> update(@RequestBody @Valid Post post){
        try {
            Post data = service.edit(post);
            return new ServiceApiResponse<>(data,ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceApiResponse<>(e);
        }
    }
@DeleteMapping("/delete/{id}")
    public ServiceApiResponse<Post> delete(@PathVariable long id){
        try {
            String data = service.delete(id);
            return new ServiceApiResponse<>(data,ResponseStatus.SUCCESS);
        }catch (Exception e){
            return new ServiceApiResponse<>(e);
        }
}
}
