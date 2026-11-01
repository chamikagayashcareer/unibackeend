package com.uni.unibakeend.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDepartmentDto {
    @NotBlank(message = "Course title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String name;

    @NotBlank(message = "Course code is required")
    @Size(min = 2, max = 20, message = "Course code must be between 2 and 20 characters")
    private String code;

    @NotBlank(message = "Description can't be empty")
    private String description;

    private String hodId;
}
