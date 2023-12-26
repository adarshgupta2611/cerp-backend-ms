package com.cerpms.studentservice.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity{
    private String courseName;
}