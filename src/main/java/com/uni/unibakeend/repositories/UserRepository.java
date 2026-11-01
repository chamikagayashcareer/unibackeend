package com.uni.unibakeend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uni.unibakeend.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("""
                select s from User s
                inner join fetch s.role r
                where r.name = 'student' and s.id = ?1
            """)
    Optional<User> findStudentById(String id);
}
