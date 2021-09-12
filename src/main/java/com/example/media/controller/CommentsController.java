package com.example.media.controller;


import com.example.media.dto.CommentsDto;
import com.example.media.models.Comments;
import com.example.media.services.CommentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    private CommentsServices commentsServices;

    @Autowired
    public CommentsController(CommentsServices commentsServices) {this.commentsServices = commentsServices;}


    @PostMapping("/comments/{id}")
    public ResponseEntity<?> createComments(@PathVariable Long id, @RequestBody CommentsDto commentsDto){
        return commentsServices.CreateComments(commentsDto, id);
    }

    @GetMapping("/getAllComments")
    public List<Comments> getAllComments(){return commentsServices.getAllComments();}


    @GetMapping("/findComment")
    public Comments findComments(Long id){
        return commentsServices.getCommentsById(id);
    }

    @DeleteMapping("/deleteComment")
    public ResponseEntity<?> deleteComment(Long id){
        return commentsServices.deleteCommentsById(id);
    }

}
