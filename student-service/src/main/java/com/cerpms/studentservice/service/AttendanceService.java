package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Attendance;
import com.cerpms.studentservice.projection.AttendanceRecord;

import java.util.List;

public interface AttendanceService {
    List<AttendanceRecord> getAttendanceService(Long studentId);

    Attendance addAttendanceService(String subjectName, Long studentId, int attendance);

    Attendance updateAttendanceService(String subjectName, Long studentId, int attendance);
}