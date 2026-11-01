package com.uni.unibakeend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.unibakeend.common.ApiResponse;
import com.uni.unibakeend.dto.department.CreateDepartmentDto;
import com.uni.unibakeend.entities.Department;
import com.uni.unibakeend.services.DepartmentService;

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
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<String>> createCourse(@Valid @RequestBody CreateDepartmentDto dto) {
        Department department = departmentService.createDepartment(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .success("Department created", department.getId()));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Department>>> getMethodName() {
        List<Department> departments = departmentService.getDepartments();
        return ResponseEntity
                .ok(ApiResponse.success(null, departments));
    }

}
