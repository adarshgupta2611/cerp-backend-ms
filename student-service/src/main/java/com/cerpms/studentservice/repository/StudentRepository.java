package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmailAndPassword(String email, String password);

    Optional<Student> findByEmail(String email);

    List<Student> findAll();
}