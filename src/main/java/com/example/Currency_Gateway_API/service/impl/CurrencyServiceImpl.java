package com.example.Currency_Gateway_API.service.impl;

import com.example.Currency_Gateway_API.model.dto.response.CurrencyFixerDTO;
import com.example.Currency_Gateway_API.model.dto.response.CurrencyDataDTO;
import com.example.Currency_Gateway_API.model.entity.CurrencyRate;
import com.example.Currency_Gateway_API.model.mapper.CurrencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.Currency_Gateway_API.repository.CurrencyRepository;
import com.example.Currency_Gateway_API.service.CurrencyService;

import java.time.Instant;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Value("${fixer.api.key}")
    private String fixerApiKey;

    @Value("${fixer.api.url}")
    private String fixerApiUrl;

    @Override
    public void fetchAndStoreCurrencyData() {
        log.info("Starting to fetch currency data from Fixer API.");

        try {
            String url = fixerApiUrl + fixerApiKey;
            ResponseEntity<CurrencyFixerDTO> response = restTemplate.getForEntity(url, CurrencyFixerDTO.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().isSuccess()) {
                log.info("Data fetched successfully, storing to database.");
                storeCurrencyData(response.getBody());
            } else {
                log.warn("Fixer API returned unsuccessful response or empty data.");
            }
        } catch (Exception e) {
            log.error("Error fetching data from Fixer API: {}", e.getMessage());
        }
    }

    @Override
    public CurrencyDataDTO getCurrentCurrencyRate(String currencyCode) {
        CurrencyRate currencyRate = currencyRepository.findTopByCurrencyOrderByTimestampDesc(currencyCode);
        return currencyMapper.toDto(currencyRate);
    }

    @Override
    public List<CurrencyDataDTO> getCurrencyHistory(String currencyCode, int hours) {
        Instant startTime = Instant.now().minusSeconds(hours * 3600L);
        List<CurrencyRate> currencyRates = currencyRepository.findByCurrencyAndTimestampAfter(currencyCode, startTime);

        return currencyRates.stream().map(currencyMapper::toDto).toList();
    }

    private void storeCurrencyData(CurrencyFixerDTO currencyFixerDTO) {
        List<CurrencyRate> currencyRateList = this.currencyMapper.toCurrencyRateList(currencyFixerDTO);
        currencyRepository.saveAll(currencyRateList);
    }
}
