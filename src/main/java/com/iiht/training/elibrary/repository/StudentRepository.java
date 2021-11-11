package com.iiht.training.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.training.elibrary.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
