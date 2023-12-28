package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Schedule;
import com.cerpms.studentservice.projection.ScheduleRequestDto;

import java.util.List;

public interface ScheduleService {
	Schedule addSchedule(ScheduleRequestDto scheduleDto, String courseName);
	List<com.cerpms.studentservice.projection.Schedule> getSchedule(String courseName);
	void editSchedule(ScheduleRequestDto scheduleDto, String courseName);
	String deleteSchedule(Long id);
}