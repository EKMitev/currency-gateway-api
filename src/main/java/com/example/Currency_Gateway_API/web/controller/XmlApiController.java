package com.example.Currency_Gateway_API.web.controller;

import com.example.Currency_Gateway_API.model.dto.request.xml.CommandDTO;
import com.example.Currency_Gateway_API.model.dto.request.RequestLogDTO;
import com.example.Currency_Gateway_API.model.dto.response.CurrencyDataDTO;
import com.example.Currency_Gateway_API.service.CurrencyService;
import com.example.Currency_Gateway_API.service.RequestLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

/**
 * Controller for handling XML-based API requests.
 * Provides endpoint for fetching current or historical currency data via XML.
 */
@RestController
@RequestMapping(value = "/xml_api", produces = MediaType.APPLICATION_XML_VALUE)
public class XmlApiController {

    private static final String SERVICE_NAME = "EXT_SERVICE_2";

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private RequestLogService requestLogService;

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> handleCommand(@Valid @RequestBody CommandDTO commandDTO) {
        if (commandDTO.getGet() != null) {
            String currency = commandDTO.getGet().getCurrency();
            logRequest(commandDTO.getId(), commandDTO.getGet().getConsumer(), Instant.now().toEpochMilli());
            CurrencyDataDTO response = currencyService.getCurrentCurrencyRate(currency);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if (commandDTO.getHistory() != null) {
            String currency = commandDTO.getHistory().getCurrency();
            int period = commandDTO.getHistory().getPeriod();
            logRequest(commandDTO.getId(), commandDTO.getHistory().getConsumer(), Instant.now().toEpochMilli());
            List<CurrencyDataDTO> response = currencyService.getCurrencyHistory(currency, period);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
