package com.example.Currency_Gateway_API.service;

import com.example.Currency_Gateway_API.model.dto.request.RequestLogDTO;

/**
 * Service interface for managing request logging and duplicate request prevention.
 * This service is responsible for checking whether a request is a duplicate and logging request details in the database.
 */
public interface RequestLogService {

    /**
     * Checks if a request with the specified request ID has already been processed.
     *
     * @param requestId The unique identifier of the request.
     * @return true if a request with the same ID has already been logged, false otherwise.
     */
    boolean isRequestDuplicated(String requestId);

    /**
     * Logs the details of an incoming request in the database.
     *
     * @param requestLogDTO A data transfer object (DTO) containing the details of the request to be logged.
     */
    void logRequest(RequestLogDTO requestLogDTO);
}
