package com.billme.currency.logger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAttemptDao extends JpaRepository<RequestAttempt, Long> {
    
}
