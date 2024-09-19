package com.example.Currency_Gateway_API.service.impl;

import com.example.Currency_Gateway_API.model.dto.request.RequestLogDTO;
import com.example.Currency_Gateway_API.model.entity.RequestLog;
import com.example.Currency_Gateway_API.model.mapper.RequestLogMapper;
import com.example.Currency_Gateway_API.repository.RequestLogRepository;
import com.example.Currency_Gateway_API.service.RequestLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private static final Logger log = LoggerFactory.getLogger(RequestLogServiceImpl.class);

    @Autowired
    private RequestLogRepository logRepository;

    @Autowired
    private RequestLogMapper mapper;

    @Override
    public boolean isRequestDuplicated(String requestId) {
        return logRepository.existsByRequestId(requestId);
    }

    @Override
    public void logRequest(RequestLogDTO requestLogDTO) {
        log.info("Logging request with ID {} for client {}.", requestLogDTO.getRequestId(), requestLogDTO.getClientId());

        RequestLog requestLog = mapper.toRequestLog(requestLogDTO);
        logRepository.save(requestLog);

        log.info("Request with ID {} has been logged successfully.", requestLogDTO.getRequestId());
    }
}
