package com.cerpms.studentservice.projection;

import lombok.Data;

@Data
public class StudentRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String password;
    private String address;
    private Long courseId;
}