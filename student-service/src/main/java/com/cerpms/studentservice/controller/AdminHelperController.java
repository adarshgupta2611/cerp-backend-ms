package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Schedule;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.ApiResponse;
import com.cerpms.studentservice.projection.ScheduleRequestDto;
import com.cerpms.studentservice.repository.AttendanceRepository;
import com.cerpms.studentservice.repository.CourseRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import com.cerpms.studentservice.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-service/adminHelp")
@CrossOrigin(origins = "http://localhost:3000/")
public class AdminHelperController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ScheduleService scheduleService;

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
    public ResponseEntity<?> getAttendanceBySubject(@PathVariable String subjectName) {
        return ResponseEntity.status(HttpStatus.OK).body(attendanceRepository.findAllBySubjectNameAndSortedById(subjectName));
    }

    @PostMapping("/{courseName}")
    public ResponseEntity<?> addSchedule(@Valid @RequestBody ScheduleRequestDto scheduledto, @PathVariable String courseName) {
        Schedule schedule = scheduleService.addSchedule(scheduledto, courseName);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
    }

    @PutMapping("/{courseName}")
    public ResponseEntity<?> editSchedule(@Valid @RequestBody ScheduleRequestDto schedule, @PathVariable String courseName) {
        scheduleService.editSchedule(schedule, courseName);
        return ResponseEntity.ok("schedule edit successfully");
    }

    @DeleteMapping("/{courseName}")
    public ApiResponse deleteEmpDetails(@Valid @RequestBody ScheduleRequestDto schedule) {
        return new ApiResponse(scheduleService.deleteSchedule(schedule.getId()));
    }

}