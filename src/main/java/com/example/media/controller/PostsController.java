package com.example.media.controller;


import com.example.media.dto.PostsDto;
import com.example.media.models.Posts;
import com.example.media.services.PostsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostsController {
    private PostsServices postsServices;

    @Autowired
    public PostsController(PostsServices postsServices) {this.postsServices = postsServices;}

    @PostMapping("/posts/{id}")
    public ResponseEntity<?> createPosts(@PathVariable Long id, @RequestBody PostsDto postsDto){
        return postsServices.CreatePosts(postsDto, id);
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<?> deletePost(Long id){
        return postsServices.DeletePostsById(id);
    }

    @GetMapping("/findPost")
    public Posts findPost (Long id){
        return postsServices.getPostsById(id);
    }

    @GetMapping("/getAllPosts")
    public List<Posts> getAllPosts(){
        return postsServices.getAllPosts();
    }
}
