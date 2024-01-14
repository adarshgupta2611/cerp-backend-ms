package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.entity.Attendance;
import com.cerpms.studentservice.projection.AttendanceRecord;
import com.cerpms.studentservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-service/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getAttendance(@PathVariable Long studentId) {
        List<AttendanceRecord> attendanceRecords = attendanceService.getAttendanceService(studentId);
        return !attendanceRecords.isEmpty()? ResponseEntity.status(200).body(attendanceRecords) : ResponseEntity.status(500).body("Attendance not found for student " + studentId);
    }

    @PostMapping("/{subjectName}")
    public ResponseEntity<?> addAttendance(@PathVariable String subjectName, @RequestBody AttendanceAddDto attendanceAddDto){
        Attendance attendance = attendanceService.addAttendanceService(subjectName, attendanceAddDto.studentId(), attendanceAddDto.attendance());
        return attendance!=null ? ResponseEntity.status(201).body(attendance): ResponseEntity.status(500).body("Attendance Already Present");
    }

    @PatchMapping("/{subjectName}/{studentId}")
    public ResponseEntity<?> updateAttendance(@PathVariable String subjectName, @PathVariable Long studentId, @RequestBody int attendance){
        Attendance updateAttendance = attendanceService.updateAttendanceService(subjectName, studentId, attendance);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateAttendance);
    }
}

record AttendanceAddDto(Long studentId,int attendance){}