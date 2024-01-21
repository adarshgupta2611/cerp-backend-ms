package com.cerpms.studentservice.projection;

import com.cerpms.studentservice.entity.Course;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttendanceRecord  implements Serializable {
    private int attendance;
    private String subjectName;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String address;
    private Course course;
}