package com.ead.authuser.controllers;


import com.ead.authuser.dtos.UserRecordDto;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    Logger logger = LogManager.getLogger(AuthenticationController.class);

    final UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserRecordDto.UserView.RegistrationPost.class)
                                                   @JsonView(UserRecordDto.UserView.RegistrationPost.class) UserRecordDto userRecordDto){
        logger.debug("POST registerUser userRecordDto received {}", userRecordDto);
        if (userService.existsByUsername(userRecordDto.username())){
            logger.warn("Username {} is already exists", userRecordDto.username());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("esse Username ja existe");
        }
        if (userService.existsByEmail(userRecordDto.email())){
            logger.warn("Username {} is already exists", userRecordDto.email());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("esse Email ja existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRecordDto));
    }

    @GetMapping("/logs")
    public String index(){
        logger.trace("TRACE");
        logger.debug("DEBUG");
        logger.info("INFO");
        logger.warn("WARN");
        logger.error("ERROR");
        return "Logging Spring Boot";
    }

}
