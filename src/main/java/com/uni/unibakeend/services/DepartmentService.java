package com.uni.unibakeend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.unibakeend.dto.department.CreateDepartmentDto;
import com.uni.unibakeend.dto.department.DepartmentResponseDto;
import com.uni.unibakeend.entities.Department;
import com.uni.unibakeend.errors.DuplicateResourceException;
import com.uni.unibakeend.repositories.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public Department createDepartment(CreateDepartmentDto dto) {

        List<Department> departments = departmentRepository.findByCode(dto.getCode());

        if (!departments.isEmpty()) {
            throw new DuplicateResourceException("Department code already in use");
        }

        Department newDepartment = modelMapper.map(dto, Department.class);

        return departmentRepository.save(newDepartment);
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponseDto> getDepartments() {
        return departmentRepository.findByIdRowDto();
    }
}
