package com.cerpms.studentservice.repository;

import com.cerpms.studentservice.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Attendance,Long> {
}