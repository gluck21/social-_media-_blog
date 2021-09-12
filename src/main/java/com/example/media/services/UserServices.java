package com.example.media.services;

import com.example.media.dto.LoginDto;
import com.example.media.dto.SignUpDto;
import com.example.media.models.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {
    ResponseEntity<?>  getAllUser();
    ResponseEntity<?> saveUser(SignUpDto signUpDto);
    ResponseEntity<?> getUserById(Long userId);
    void deleteUserById(Long userId);
    ResponseEntity Login(LoginDto loginDto);
    Long userLikeAPost(Long usersId, Long postsId);
}
