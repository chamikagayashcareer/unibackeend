package com.uni.unibakeend.dto.batch;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AssignStudentRequest {
    @NotNull(message = "Student Id cannot be empty")
    private String studentId;

    @NotNull(message = "Batch Id cannot be empty")
    private String batchId;
}
