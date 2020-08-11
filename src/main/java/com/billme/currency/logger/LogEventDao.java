package com.billme.currency.logger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEventDao extends JpaRepository<LogEvent, Long> {

    List<LogEvent> findAllByOrderByIdDesc();
    
}
