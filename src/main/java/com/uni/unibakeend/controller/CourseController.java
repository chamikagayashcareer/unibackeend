package com.uni.unibakeend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.unibakeend.common.ApiResponse;
import com.uni.unibakeend.dto.course.CourseResponseDto;
import com.uni.unibakeend.dto.course.CreateCourseDto;
import com.uni.unibakeend.entities.Course;
import com.uni.unibakeend.services.CourseServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {

    private final CourseServices courseServices;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<String>> createCourse(@Valid @RequestBody CreateCourseDto dto) {
        Course course = courseServices.createCourse(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .success("Course created", course.getId()));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CourseResponseDto>>> getMethodName() {
        List<CourseResponseDto> courses = courseServices.getCourses();

        return ResponseEntity
                .ok(ApiResponse.success(null, courses));
    }

}
