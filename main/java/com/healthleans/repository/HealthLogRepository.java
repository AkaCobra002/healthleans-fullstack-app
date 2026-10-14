package com.healthleans.repository;

import com.healthleans.model.HealthLog;
import com.healthleans.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {

    List<HealthLog> findByUserOrderByDateDesc(User user);
}