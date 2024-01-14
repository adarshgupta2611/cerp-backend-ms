package com.cerpms.adminservice.controller;

import com.cerpms.adminservice.entity.Admin;
import com.cerpms.adminservice.projection.CourseDTO;
import com.cerpms.adminservice.projection.ScheduleRequestDto;
import com.cerpms.adminservice.projection.SubjectDTO;
import com.cerpms.adminservice.service.AdminService;
import com.cerpms.adminservice.service.FeignInterfaceClient;
import com.cerpms.adminservice.service.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin-service/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private FeignInterfaceClient feignInterfaceClient;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody @Valid Admin admindto) {
        Optional<Admin> admin = adminService.authenticateAdmin(admindto.getEmail(), admindto.getPassword());
        return admin.isPresent() ? ResponseEntity.status(200).body(Map.of("id", admin.get().getId(), "token", jwtHelper.generateToken(admin.get().getEmail()))) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody String email) {
        return ResponseEntity.ok(adminService.findByEmail(email).getEmail());
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getCourseList() {
        List<CourseDTO> courseList = feignInterfaceClient.getCourseList();
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/courses/{courseName}")
    public ResponseEntity<?> getSubjectList(@PathVariable String courseName) {
        List<SubjectDTO> subjectDTOList = feignInterfaceClient.getSubjectList(courseName);
        return ResponseEntity.ok(subjectDTOList);
    }

    @GetMapping("/attendance/{subjectName}")
    public ResponseEntity<?> getAttendanceBySubject(@PathVariable String subjectName) {
        return ResponseEntity.ok(feignInterfaceClient.getAttendanceBySubject(subjectName));
    }

    @PostMapping("/{courseName}")
    public ResponseEntity<?> addSchedule(@Valid @RequestBody ScheduleRequestDto scheduledto, @PathVariable String courseName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feignInterfaceClient.addSchedule(scheduledto, courseName));
    }

    @PutMapping("/{courseName}")
    public ResponseEntity<?> editSchedule(@Valid @RequestBody ScheduleRequestDto schedule, @PathVariable String courseName) {
        feignInterfaceClient.editSchedule(schedule, courseName);
        return ResponseEntity.ok("schedule edit successfully");
    }

    @DeleteMapping("/{courseName}")
    public ResponseEntity<?> deleteEmpDetails(@Valid @RequestBody ScheduleRequestDto schedule) {
        return ResponseEntity.status(204).body(feignInterfaceClient.deleteEmpDetails(schedule));
    }

}