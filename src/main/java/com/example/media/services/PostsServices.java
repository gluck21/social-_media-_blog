package com.example.media.services;

import com.example.media.dto.PostsDto;
import com.example.media.models.Posts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostsServices {
    ResponseEntity<?> CreatePosts(PostsDto postsDto, Long id);
    List<Posts> getAllPosts();
    Posts getPostsById(Long id);
    ResponseEntity<?> DeletePostsById(Long id);
}
