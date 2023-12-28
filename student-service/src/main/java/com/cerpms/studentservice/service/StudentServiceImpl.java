package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.projection.StudentRequestDto;
import com.cerpms.studentservice.repository.CourseRepository;
import com.cerpms.studentservice.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(StudentRequestDto studentdto, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        Student st = studentRepository.findByEmailAndPassword(studentdto.getEmail(), studentdto.getPassword());
        if (st == null) {
            Student student = new Student();
            student.setFirstName(studentdto.getFirstName());
            student.setLastName(studentdto.getLastName());
            student.setEmail(studentdto.getEmail());
            student.setGender(studentdto.getGender());
            student.setPassword(studentdto.getPassword());
            student.setAddress(studentdto.getAddress());
            student.setCourse(course);
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    @Override
    @Cacheable(cacheNames = "student_details", key = "#studentId")
    public Student getStudentDetails(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
    }

    @Override
    public Student authenticateStudent(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }
}