package com.uni.unibakeend.dto.course;

import java.time.LocalDate;

public interface CourseResponseDto {
    String getId();

    String getName();

    String getCode();

    int getDuration();

    String getDescription();

    @org.springframework.beans.factory.annotation.Value("#{target.department?.id}")
    String getDepartmentId();

    LocalDate getCreatedAt();

}
