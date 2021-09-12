package com.example.media.services;

import com.example.media.dto.CommentsDto;
import com.example.media.models.Comments;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentsServices {
    ResponseEntity<?> CreateComments(CommentsDto commentsDto, Long id);
    ResponseEntity<?> deleteCommentsById(Long id);
    Comments getCommentsById(Long id);
    List<Comments> getAllComments();
}
