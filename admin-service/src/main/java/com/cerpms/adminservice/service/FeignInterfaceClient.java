package com.cerpms.adminservice.service;

import com.cerpms.adminservice.projection.CourseDTO;
import com.cerpms.adminservice.projection.SubjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE")
public interface FeignInterfaceClient {

    @GetMapping("/adminHelp/courses")
    List<CourseDTO> getCourseList();

    @GetMapping("/adminHelp/courses/{courseName}")
    List<SubjectDTO> getSubjectList(@PathVariable String courseName);

    @GetMapping("/adminHelp/attendance/{subjectName}")
    List<Object> getAttendanceBySubject(@PathVariable String subjectName);

}