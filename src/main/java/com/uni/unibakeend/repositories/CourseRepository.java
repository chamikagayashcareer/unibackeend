package com.uni.unibakeend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.unibakeend.dto.course.CourseResponseDto;
import com.uni.unibakeend.entities.Course;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    List<Course> findByCode(String code);

    List<CourseResponseDto> findAllProjectBy();

}
