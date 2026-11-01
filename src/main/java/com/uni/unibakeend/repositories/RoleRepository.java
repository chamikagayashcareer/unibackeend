package com.uni.unibakeend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uni.unibakeend.dto.role.RoleResponse;
import com.uni.unibakeend.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);

    @Query("""
                select r.id as id, r.name as name
                from Role r
            """)
    List<RoleResponse> findAllList();
}