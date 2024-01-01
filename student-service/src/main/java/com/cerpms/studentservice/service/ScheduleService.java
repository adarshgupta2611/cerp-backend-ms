package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Schedule;
import com.cerpms.studentservice.projection.ScheduleRequestDto;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getSchedule(String courseName);

    Schedule addSchedule(ScheduleRequestDto scheduledto, String courseName);

    void editSchedule(ScheduleRequestDto scheduleDto, String courseName);

    String deleteSchedule(Long id);

}