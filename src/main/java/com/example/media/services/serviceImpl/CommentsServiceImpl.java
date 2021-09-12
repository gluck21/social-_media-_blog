package com.example.media.services.serviceImpl;

import com.example.media.dto.CommentsDto;
import com.example.media.dto.ResponseBody;
import com.example.media.exceptions.AppExceptions;
import com.example.media.models.Comments;
import com.example.media.models.Posts;
import com.example.media.repository.CommentsRepository;
import com.example.media.repository.PostsRepository;
import com.example.media.services.CommentsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentsServiceImpl implements CommentsServices {
    private final CommentsRepository commentsRepository;
    private  final PostsRepository postsRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository, PostsRepository postsRepository) {
        this.commentsRepository = commentsRepository;
        this.postsRepository = postsRepository;
    }


    @Override
    public ResponseEntity<?> CreateComments(CommentsDto commentsDto, Long id) {
        Optional<Posts> posts = postsRepository.findById(id);
        if (posts.isPresent()){
            Comments comments = new Comments();
            comments.setMessage(commentsDto.getMessage());
            if (comments.getMessage().isBlank()){
                throw new AppExceptions("Please type a message", HttpStatus.NO_CONTENT);
            }
            Comments comments1 = commentsRepository.save(comments);
            posts.get().getComments().add(comments1);
            postsRepository.save(posts.get());
            return new ResponseEntity<>(new ResponseBody<>("Created", comments1), HttpStatus.CREATED);
        }
        throw new AppExceptions("Please sign up", HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<?> deleteCommentsById(Long id) {
       commentsRepository.deleteById(id);
        ResponseBody<Comments> responseBody = new ResponseBody<>();
        responseBody.setMessage("Comment deleted");
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public Comments getCommentsById(Long id) {
        Optional<Comments> optional = commentsRepository.findById(id);
        Comments comments = null;
        if (optional.isPresent()){
            comments = optional.get();
        }else {
            throw new AppExceptions("Comment does not exist", HttpStatus.BAD_REQUEST);
        }
        return comments;
    }

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }
}
