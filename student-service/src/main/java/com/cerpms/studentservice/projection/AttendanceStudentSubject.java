package com.cerpms.studentservice.projection;

import com.cerpms.studentservice.entity.Student;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttendanceStudentSubject  implements Serializable {
    private Student student;
    private int attendance;
}