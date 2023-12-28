package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.entity.Student;
import com.cerpms.studentservice.projection.StudentCredentials;
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


	@PostMapping("/signup")
	public ResponseEntity<?> studentRegistration(@Valid @RequestBody StudentRequestDto student){
		Student st = studentService.addStudent(student, student.getCourseId());
		if(st!=null) {
		return ResponseEntity.status(HttpStatus.CREATED).body(st);
		}
		else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Student already signed up");
		}
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<?> getStudentDetails(@PathVariable Long studentId){
		return ResponseEntity.ok(studentService.getStudentDetails(studentId));
	}
	
	@PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody StudentCredentials studentCredential) {
		
		Student student =studentService.authenticateStudent(studentCredential.getEmail(), studentCredential.getPassword());

        if (student != null) {
            return ResponseEntity.ok(Map.of("id",student.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    } 
}