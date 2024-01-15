package com.cerpms.studentservice.service;

import com.cerpms.studentservice.entity.Course;
import com.cerpms.studentservice.entity.Schedule;
import com.cerpms.studentservice.entity.Subject;
import com.cerpms.studentservice.projection.ScheduleRequestDto;
import com.cerpms.studentservice.repository.CourseRepository;
import com.cerpms.studentservice.repository.ScheduleRepository;
import com.cerpms.studentservice.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Cacheable(cacheNames = "student_schedule_getSchedule", key = "#courseName")
    public List<Schedule> getSchedule(String courseName) {
        return scheduleRepository.findFullScheduleByCourse(courseName);
    }

    @Override
    public Schedule addSchedule(ScheduleRequestDto scheduleDto, String courseName) {
        Course course = courseRepository.findByCourseName(courseName);
        Subject subject = subjectRepository.findBySubjectName(scheduleDto.getSubjectName()).get();
        Schedule schedule = new Schedule();
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEndTime(scheduleDto.getEndTime());
        schedule.setLocation(scheduleDto.getLocation());
        schedule.setCourse(course);
        schedule.setSubject(subject);
        return scheduleRepository.save(schedule);
    }

    @Override
    public void editSchedule(ScheduleRequestDto scheduleDto, String courseName) {
        Subject subject = subjectRepository.findBySubjectName(scheduleDto.getSubjectName()).get();
        Course course = courseRepository.findByCourseName(courseName);
        Schedule editedSchedule = scheduleRepository.findById(scheduleDto.getId()).get();
        if (scheduleDto.getStartTime() != null)
            editedSchedule.setStartTime(scheduleDto.getStartTime());
        if (scheduleDto.getEndTime() != null)
            editedSchedule.setEndTime(scheduleDto.getEndTime());
        if (scheduleDto.getLocation() != null)
            editedSchedule.setLocation(scheduleDto.getLocation());
        if (subject != null)
            editedSchedule.setSubject(subject);
        editedSchedule.setCourse(course);
    }

    @Override
    public String deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return "Schedule deleted successfully";
        }
        return "Deletion Failed : Invalid Schedule Id";
    }
}