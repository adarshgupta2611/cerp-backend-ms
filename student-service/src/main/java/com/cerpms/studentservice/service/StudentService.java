package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.projection.StudentRequestDto;

public interface StudentService {
	Student addStudent(StudentRequestDto student, Long courseId);
	Student getStudentDetails(Long studentId);
	Student authenticateStudent(String email, String password);
}