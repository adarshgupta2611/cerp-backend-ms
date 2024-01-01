package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select new com.cerpms.studentservice.entity.Schedule(sc.id as scheduleId, sc.startTime as startTime, sc.endTime as endTime, sc.location as location, sb.subjectName as subjectName) from Schedule sc join sc.subject sb where sc.course.courseName= :courseName")
    List<Schedule> findFullScheduleByCourse(@Param("courseName") String courseName);

}