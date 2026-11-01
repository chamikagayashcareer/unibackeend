package com.uni.unibakeend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.unibakeend.dto.course.CourseResponseDto;
import com.uni.unibakeend.dto.course.CreateCourseDto;
import com.uni.unibakeend.entities.Course;
import com.uni.unibakeend.entities.Department;
import com.uni.unibakeend.errors.DuplicateResourceException;
import com.uni.unibakeend.repositories.CourseRepository;
import com.uni.unibakeend.repositories.DepartmentRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class CourseServices {

    private final CourseRepository courseRepository;

    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public Course createCourse(CreateCourseDto dto) {

        List<Course> existingCourses = courseRepository.findByCode(dto.getCode());

        if (!existingCourses.isEmpty()) {
            throw new DuplicateResourceException("Course code already in use");
        }

        Course newCourse = modelMapper.map(dto, Course.class);

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        newCourse.setDepartment(department);

        return courseRepository.save(newCourse);
    }

    @Transactional(readOnly = true)
    public List<CourseResponseDto> getCourses() {
        return courseRepository.findAllProjectBy();
    }

}
