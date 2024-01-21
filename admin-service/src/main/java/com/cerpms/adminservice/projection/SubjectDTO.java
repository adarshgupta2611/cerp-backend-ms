package com.cerpms.adminservice.projection;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectDTO implements Serializable {
    private String subjectName;
    private Long id;
}