package com.cerpms.adminservice.projection;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO implements Serializable {
    private Long id;
    private String courseName;
}