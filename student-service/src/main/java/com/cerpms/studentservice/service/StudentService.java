package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.projection.StudentRequestDto;

public interface StudentService {
    Student getStudentService(Long studentId);

    Student signService(String email, String password);

    Student signupService(StudentRequestDto request);
}