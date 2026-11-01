package com.uni.unibakeend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.unibakeend.entities.Department;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    List<Department> findByCode(String code);

}
