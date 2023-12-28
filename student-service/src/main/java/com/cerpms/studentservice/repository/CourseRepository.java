package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
	Course findByCourseName(String courseName);
}