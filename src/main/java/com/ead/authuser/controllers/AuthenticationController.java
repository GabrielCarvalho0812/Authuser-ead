package com.ead.authuser.controllers;


import com.ead.authuser.dtos.UserRecordDto;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    final UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                                   @JsonView(UserRecordDto.UserView.RegistrationPost.class) UserRecordDto userRecordDto){
        if (userService.existsByUsername(userRecordDto.username())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("esse Username ja existe");
        }
        if (userService.existsByEmail(userRecordDto.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("esse Email ja existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRecordDto));
    }



}
