package com.cerpms.adminservice.service;

import com.cerpms.adminservice.projection.CourseDTO;
import com.cerpms.adminservice.projection.ScheduleRequestDto;
import com.cerpms.adminservice.projection.SubjectDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE")
public interface FeignInterfaceClient {

    @GetMapping("/adminHelp/courses")
    List<CourseDTO> getCourseList();

    @GetMapping("/adminHelp/courses/{courseName}")
    List<SubjectDTO> getSubjectList(@PathVariable String courseName);

    @GetMapping("/adminHelp/attendance/{subjectName}")
    List<Object> getAttendanceBySubject(@PathVariable String subjectName);

    @PostMapping("/adminHelp/{courseName}")
    Object addSchedule(@Valid @RequestBody ScheduleRequestDto scheduledto, @PathVariable String courseName);

    @PutMapping("/adminHelp/{courseName}")
    Object editSchedule(@Valid @RequestBody ScheduleRequestDto schedule, @PathVariable String courseName);

    @DeleteMapping("/adminHelp/{courseName}")
    Object deleteEmpDetails(@Valid @RequestBody ScheduleRequestDto schedule);

}