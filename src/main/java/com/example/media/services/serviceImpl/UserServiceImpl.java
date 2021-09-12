package com.example.media.services.serviceImpl;

import com.example.media.dto.LoginDto;
import com.example.media.dto.ResponseBody;
import com.example.media.dto.SignUpDto;
import com.example.media.exceptions.AppExceptions;
import com.example.media.models.Posts;
import com.example.media.models.PostsLikes;
import com.example.media.models.Users;
import com.example.media.repository.PostsLikesRepository;
import com.example.media.repository.PostsRepository;
import com.example.media.repository.UserRepository;
import com.example.media.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

  private final UserRepository userRepository;
  private final PostsLikesRepository postsLikesRepository;
  private final PostsRepository postsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostsLikesRepository postsLikesRepository, PostsRepository postsRepository) {
        this.userRepository = userRepository;
        this.postsLikesRepository = postsLikesRepository;
        this.postsRepository = postsRepository;
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveUser(SignUpDto signUpDto) throws AppExceptions{
        Users users = new Users();
        users.setName(signUpDto.getName());
        users.setEmail(signUpDto.getEmail());
        users.setPassword(signUpDto.getPassword());
        ResponseBody responseBody = new ResponseBody();
        Optional<Users> dbUser = userRepository.getUserByEmailAndPassword(users.getEmail(), users.getPassword());
        if (dbUser.isEmpty()){
            userRepository.save(users);
            responseBody.setMessage("Registration successful");
            responseBody.setData(users);
            return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        }
        throw new AppExceptions("Email already in use",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getUserById(Long userId) {
        return new ResponseEntity<>(userRepository.findById(userId).get(), HttpStatus.OK);
    }

    @Override
    public void deleteUserById(Long userId) {
    }

    @Override
    public ResponseEntity<ResponseBody<Users>> Login(LoginDto loginDto) throws AppExceptions{
        Optional<Users> dbUsers = userRepository.getUserByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
        if (dbUsers.isPresent()){
           ResponseBody<Users> responseBody = new ResponseBody<>();
           responseBody.setMessage("Welcome");
           responseBody.setData(dbUsers.get());
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        throw new AppExceptions("Password or Email is not correct",HttpStatus.BAD_REQUEST);
    }

    @Override
    public Long userLikeAPost(Long usersId, Long postsId) {
        Optional<PostsLikes> postsLikes = postsLikesRepository.findAllByUsersIdAndPostsId(usersId, postsId);
        if (postsLikes.isPresent()){
            postsLikesRepository.delete(postsLikes.get());
          return   postsLikesRepository.count();

        }
        Posts posts = postsRepository.findById(postsId).get();
        Users users = userRepository.findById(usersId).get();
        PostsLikes postsLikes1 = new PostsLikes();
               postsLikes1.setPosts(posts);
               postsLikes1.setUsers(users);
               postsLikesRepository.save(postsLikes1);
               return postsLikesRepository.count();
    }
}
