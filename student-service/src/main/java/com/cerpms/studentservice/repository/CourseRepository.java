package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseName(String courseName);

    Optional<Course> findById(Long courseId);
}