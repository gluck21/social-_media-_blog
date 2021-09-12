package com.example.media.services.serviceImpl;

import com.example.media.dto.PostsDto;
import com.example.media.dto.ResponseBody;
import com.example.media.exceptions.AppExceptions;
import com.example.media.models.Posts;
import com.example.media.models.Users;
import com.example.media.repository.PostsRepository;
import com.example.media.repository.UserRepository;
import com.example.media.services.PostsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsServices {
    private final UserRepository userRepository;
   private final PostsRepository postsRepository;


   @Autowired
    public PostsServiceImpl(UserRepository userRepository, PostsRepository postsRepository) {
       this.userRepository = userRepository;
       this.postsRepository = postsRepository;
    }

    @Override
    public ResponseEntity<?> CreatePosts(PostsDto postsDto, Long id) {
      Optional<Users> users = userRepository.findById(id);
      if (users.isPresent()){
       Posts posts = new Posts();
          posts.setMessage(postsDto.getMessage());
       if (posts.getMessage().isBlank()){
           throw new AppExceptions("Please type a message", HttpStatus.NO_CONTENT);
       }
         Posts posts1 = postsRepository.save(posts);
         users.get().getUserPosts().add(posts1);
         userRepository.save(users.get());
          return new ResponseEntity<>(new ResponseBody<>("created",posts1),HttpStatus.CREATED);
      }
      throw new AppExceptions("Please Sign up", HttpStatus.FORBIDDEN);
    }

    @Override
    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public Posts getPostsById(Long id) {
       Optional<Posts> optional = postsRepository.findById(id);
       Posts posts = null;
       if (optional.isPresent()){
           posts = optional.get();
       }else {
           throw new AppExceptions("Post does not exist", HttpStatus.BAD_REQUEST);
       }
       return posts;
    }

    @Override
    public ResponseEntity<?> DeletePostsById(Long id) {
       postsRepository.deleteById(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }
}
