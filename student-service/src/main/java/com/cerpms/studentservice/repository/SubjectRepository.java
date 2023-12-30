package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByCourse(Course course);

    Optional<Subject> findBySubjectName(String subjectName);
}