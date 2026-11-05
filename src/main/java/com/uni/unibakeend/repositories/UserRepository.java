package com.uni.unibakeend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uni.unibakeend.dto.user.UserResponse;
import com.uni.unibakeend.entities.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    @Query("""
                select u.id as id,
                p.firstName as firstName,
                p.lastName as lastName,
                u.email as email,
                d.id as departmentId,
                d.name as departmentName,
                b.id as batchId,
                b.name as batchName,
                r as roles
                from User u
                left join u.profile p
                left join u.department d
                left join u.batch b
                left join u.roles r
            """)
    List<UserResponse> findAllUsers();
}
