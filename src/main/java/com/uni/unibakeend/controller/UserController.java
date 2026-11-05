package com.uni.unibakeend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.unibakeend.common.ApiResponse;
import com.uni.unibakeend.dto.user.AddUserDto;
import com.uni.unibakeend.dto.user.UpdateUserDto;
import com.uni.unibakeend.dto.user.UserResponse;
import com.uni.unibakeend.dto.user.UserResponseDto;
import com.uni.unibakeend.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("add")
    public ResponseEntity<ApiResponse<String>> addUser(@Valid @RequestBody AddUserDto dto) {
        String userId = userService.addUser(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User added", userId));
    }

    @PatchMapping("update")
    public ResponseEntity<ApiResponse<String>> updatedAddedUser(@Valid @RequestBody UpdateUserDto dto) {
        String userId = userService.updateAddedUser(dto);

        return ResponseEntity.ok(ApiResponse.success("User updated", userId));

    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getUsers() {
        return ResponseEntity
                .ok(ApiResponse.success(null, userService.getUsers()));
    }

}
