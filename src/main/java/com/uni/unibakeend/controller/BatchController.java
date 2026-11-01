package com.uni.unibakeend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni.unibakeend.common.ApiResponse;
import com.uni.unibakeend.dto.batch.AssignStudentRequest;
import com.uni.unibakeend.dto.batch.BatchDto;
import com.uni.unibakeend.dto.batch.CreateBatchDto;
import com.uni.unibakeend.entities.Batch;
import com.uni.unibakeend.services.BatchServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/batch")
@AllArgsConstructor
public class BatchController {

    private final BatchServices batchServices;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<String>> createBatch(@Valid @RequestBody CreateBatchDto dto) {
        Batch batch = batchServices.createBatch(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse
                        .success("Course created", batch.getId()));

    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<BatchDto>>> getMethodName() {
        List<BatchDto> batches = batchServices.getBatches();

        return ResponseEntity
                .ok(ApiResponse
                        .success(null, batches));
    }

    @PatchMapping("/student/assign")
    public void assignStudent(@RequestBody AssignStudentRequest req) {
        batchServices.assignStudent(req);
    }

    @DeleteMapping("{id}")
    public void deleteBatch(@PathVariable String id) {
        batchServices.deleteBatch(id);
    }

}
