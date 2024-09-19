package com.example.Currency_Gateway_API.service;


import com.example.Currency_Gateway_API.model.dto.response.CurrencyDataDTO;

import java.util.List;

/**
 * Service interface for managing currency-related operations.
 * This service interacts with the Fixer.io API and the database to retrieve and store currency data.
 */
public interface CurrencyService {

    /**
     * Fetches the latest currency data from the external Fixer.io API and stores it in the database.
     * This method is called on a scheduled interval to keep currency data up-to-date.
     */
    void fetchAndStoreCurrencyData();

    /**
     * Fetches the current exchange rate for the specified currency.
     *
     * @param currencyCode The currency code (e.g., EUR, USD) for which to fetch the current exchange rate.
     * @return The latest exchange rate data for the specified currency.
     */
    CurrencyDataDTO getCurrentCurrencyRate(String currencyCode);

    /**
     * Fetches the historical exchange rate data for a specified currency over a given period.
     *
     * @param currencyCode The currency code (e.g., EUR, USD) for which to fetch the historical data.
     * @param hours        The number of hours in the past for which to retrieve data (e.g., last 24 hours).
     * @return A list of exchange rate data points for the specified period.
     */
    List<CurrencyDataDTO> getCurrencyHistory(String currencyCode, int hours);
}
