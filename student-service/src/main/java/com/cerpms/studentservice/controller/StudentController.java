package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.projection.StudentRequestDto;
import com.cerpms.studentservice.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentService(studentId));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid Credential credential) {
        Student s = studentService.signService(credential.email(), credential.password());
        return ResponseEntity.ok(Map.of("id", s.getId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> studentRegistration(@Valid @RequestBody StudentRequestDto student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.signupService(student));
    }


}

record Credential(String email, String password) {
}