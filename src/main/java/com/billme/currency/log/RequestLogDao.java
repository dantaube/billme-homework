package com.billme.currency.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogDao extends JpaRepository<RequestLog, Long> {
    
}
