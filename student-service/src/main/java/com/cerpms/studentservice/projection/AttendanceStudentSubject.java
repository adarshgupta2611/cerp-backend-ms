package com.cerpms.studentservice.projection;

import com.cerpms.studentservice.entity.Student;
import lombok.Data;

@Data
public class AttendanceStudentSubject {
    private Student student;
    private int attendance;
}