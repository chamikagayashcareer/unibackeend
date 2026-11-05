package com.uni.unibakeend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.uni.unibakeend.dto.department.DepartmentResponseDto;
import com.uni.unibakeend.entities.Department;
import com.uni.unibakeend.entities.User;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    List<Department> findByCode(String code);

    @Modifying
    @Query("UPDATE Department d SET d.hod = ?1 WHERE d.id = ?2")
    void updateHod(User hod, String departmentId);

    @Query("""
                select d.id as id,
                d.name as name,
                d.code as code,
                d.description as description,
                p.firstName as hodFirstName,
                p.lastName as hodLastName,
                count(c) as coursesCount
                from Department d
                left join d.hod h
                left join d.courses c
                left join h.profile p
                Group by d
            """)
    List<DepartmentResponseDto> findByIdRowDto();

}
