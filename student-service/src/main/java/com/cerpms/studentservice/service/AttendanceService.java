package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Attendance;
import com.cerpms.studentservice.projection.AttendanceList;
import com.cerpms.studentservice.projection.AttendanceRecord;
import com.cerpms.studentservice.projection.AttendanceRequestDto;

import java.util.List;

public interface AttendanceService {
    List<AttendanceRecord> showAttendanceByStudent(Long studentId);

    Attendance addAttendance(AttendanceRequestDto attendancedto, String subjectName);

    List<AttendanceList> showAttendance(String subjectName);

    void updateAttendance(int attendance, String subjectName, Long studentId);
}