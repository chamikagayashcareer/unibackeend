package com.uni.unibakeend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uni.unibakeend.entities.Batch;

public interface BatchRepository extends JpaRepository<Batch, String> {

    List<Batch> findAllByName(String name);

    @Query("""
            select distinct b from Batch b
                left join fetch b.course
                left join fetch b.students s
                left join fetch s.profile
            """)
    List<Batch> findAllBatchStudentRows();

}