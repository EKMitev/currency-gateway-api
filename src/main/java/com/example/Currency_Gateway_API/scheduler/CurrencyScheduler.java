package com.example.Currency_Gateway_API.scheduler;

import com.example.Currency_Gateway_API.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CurrencyScheduler {

    @Autowired
    private CurrencyService currencyService;

   // @Scheduled(cron = "0 0 * * * *")
    @Scheduled(cron = "0 * * * * *")
    public void scheduleCurrencyDataFetch() {
        currencyService.fetchAndStoreCurrencyData();
    }
}
