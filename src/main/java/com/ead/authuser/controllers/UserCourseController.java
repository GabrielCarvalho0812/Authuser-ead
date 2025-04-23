package com.ead.authuser.controllers;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.CourseRecordDto;
import com.ead.authuser.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserCourseController {

    final CourseClient courseClient;
    final UserService userService;


    public UserCourseController(CourseClient courseClient, UserService userService) {
        this.courseClient = courseClient;
        this.userService = userService;
    }


    //traga todos courses relacionados a um determinado usuario
    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseRecordDto>> getAllCoursesByUser(@PageableDefault(sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
                                                                     @PathVariable(value = "userId") UUID userId){
        userService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllCoursesByUser(userId, pageable));
    }

}
