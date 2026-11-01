package com.uni.unibakeend.dto.batch;

import java.math.BigInteger;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBatchDto {
    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Intake Year cannot be empty")
    private BigInteger intakeYear;

    @NotNull(message = "Course cannot be empty")
    private String courseId;
}
