package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.repository.AttendanceRepository;
import com.cerpms.studentservice.repository.CourseRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminHelp")
@CrossOrigin(origins = "http://localhost:3000/")
public class AdminHelperController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/courses")
    public ResponseEntity<?> getCourseList() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @GetMapping("/courses/{courseName}")
    public ResponseEntity<?> getSubjectList(@PathVariable String courseName) {
        Course course = courseRepository.findByCourseName(courseName);
        List<Subject> subjects = subjectRepository.findAllByCourse(course);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/attendance/{subjectName}")
    public ResponseEntity<?> getAttendanceBySubject(@PathVariable String subjectName){
        return ResponseEntity.status(HttpStatus.OK).body(attendanceRepository.findAllBySubjectNameAndSortedById(subjectName));
    }

}