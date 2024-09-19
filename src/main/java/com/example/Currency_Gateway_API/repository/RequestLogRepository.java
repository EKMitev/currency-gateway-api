package com.example.Currency_Gateway_API.repository;

import com.example.Currency_Gateway_API.model.entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {

    boolean existsByRequestId(String requestId);
}
