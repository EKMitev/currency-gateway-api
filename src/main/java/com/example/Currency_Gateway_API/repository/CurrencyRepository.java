package com.example.Currency_Gateway_API.repository;

import com.example.Currency_Gateway_API.model.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyRate, Long> {

    CurrencyRate findTopByCurrencyOrderByTimestampDesc(String currency);

    List<CurrencyRate> findByCurrencyAndTimestampAfter(String currencyCode, Instant instant);
}
