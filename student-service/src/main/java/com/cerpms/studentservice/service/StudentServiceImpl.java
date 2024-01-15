package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.projection.StudentRequestDto;
import com.cerpms.studentservice.repository.CourseRepository;
import com.cerpms.studentservice.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(cacheNames = "student_getStudentService", key = "#studentId")
    public Student getStudentService(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Student not found for ID " + studentId));
    }

    @Override
    public Student signService(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new NoSuchElementException("Invalid Email or Password"));
    }

    @Override
    public Student signupService(StudentRequestDto request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new NoSuchElementException("Course Not found for ID " + request.getCourseId()));
        Student newStudent = modelMapper.map(request, Student.class);
        newStudent.setCourse(course);
        studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    @Cacheable(cacheNames = "student_findByEmail", key = "#email")
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Student not found for ID " + email));
    }
}