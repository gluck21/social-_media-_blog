package com.example.media.controller;

import com.example.media.dto.LoginDto;
import com.example.media.dto.ResponseBody;
import com.example.media.dto.SignUpDto;
import com.example.media.exceptions.AppExceptions;
import com.example.media.models.Users;
import com.example.media.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UsersController {
    private UserServices userServices;

    @Autowired
    public UsersController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        return userServices.saveUser(signUpDto);

    }

    @PostMapping("/login")
    public ResponseEntity<?>Login(@RequestBody LoginDto loginDto, HttpSession httpSession){
        ResponseEntity<ResponseBody<Users>> responseEntity = userServices.Login(loginDto);
        ResponseBody<Users> responseBody =  responseEntity.getBody();
        assert responseBody != null;
        Users users = responseBody.getData();
        httpSession.setAttribute("users",users);
        return responseEntity;
    }

    @PostMapping("/LikePost")
    public Long LikeAPost(Long usersId, Long postsId){
        return userServices.userLikeAPost(usersId,postsId);
    }

//    @GetMapping("/allUser")
//        public ResponseEntity getAllUsers(HttpSession httpSession){
//        Users user = (Users) httpSession.getAttribute("user");
//        if (user.getRole().getERole() == ERole.ADMIN){
//            return userServices.getAllUser();
//        }
//        throw new AppExceptions("Unauthorised access", HttpStatus.FORBIDDEN);
//    }


}
