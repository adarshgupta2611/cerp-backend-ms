package com.cerpms.studentservice.projection;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentRequestDto implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String password;
    private String address;
    private Long courseId;
}