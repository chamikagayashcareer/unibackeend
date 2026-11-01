package com.uni.unibakeend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.unibakeend.common.ApiResponse;
import com.uni.unibakeend.dto.role.RoleResponseDto;
import com.uni.unibakeend.services.RoleService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/role")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RoleResponseDto>>> getMethodName() {
        List<RoleResponseDto> roles = roleService.getRoles();

        return ResponseEntity.ok(ApiResponse.success(null, roles));
    }

}
