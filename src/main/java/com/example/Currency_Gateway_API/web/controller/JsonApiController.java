package com.example.Currency_Gateway_API.web.controller;

import com.example.Currency_Gateway_API.model.dto.request.CurrencyHistoryRequestDTO;
import com.example.Currency_Gateway_API.model.dto.request.CurrencyRequestDTO;
import com.example.Currency_Gateway_API.model.dto.request.RequestLogDTO;
import com.example.Currency_Gateway_API.model.dto.response.CurrencyDataDTO;
import com.example.Currency_Gateway_API.service.CurrencyService;
import com.example.Currency_Gateway_API.service.RequestLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling JSON-based API requests.
 * Provides endpoints for fetching current and historical currency data.
 */
@RestController
@RequestMapping("/json_api")
public class JsonApiController {

    private static final String SERVICE_NAME = "EXT_SERVICE_1";

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private RequestLogService requestLogService;

    @PostMapping("/current")
    public ResponseEntity<CurrencyDataDTO> getCurrentCurrencyData(@Valid @RequestBody CurrencyRequestDTO request) {
        logRequest(request.getRequestId(), request.getClient(), request.getTimestamp());
        CurrencyDataDTO response = currencyService.getCurrentCurrencyRate(request.getCurrency());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/history")
    public ResponseEntity<List<CurrencyDataDTO>> getCurrencyHistory(@Valid @RequestBody CurrencyHistoryRequestDTO request) {
        logRequest(request.getRequestId(), request.getClient(), request.getTimestamp());
        List<CurrencyDataDTO> response = currencyService.getCurrencyHistory(request.getCurrency(), request.getPeriod());

        return ResponseEntity.ok(response);
    }

    private void logRequest(String requestId, String clientId, Long timestamp) {
        RequestLogDTO requestLogDto = new RequestLogDTO(
                requestId,
                SERVICE_NAME,
                clientId,
                timestamp
        );
        requestLogService.logRequest(requestLogDto);
    }
}
