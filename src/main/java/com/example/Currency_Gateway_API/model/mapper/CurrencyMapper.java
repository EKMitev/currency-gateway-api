package com.example.Currency_Gateway_API.model.mapper;

import com.example.Currency_Gateway_API.model.dto.response.CurrencyDataDTO;
import com.example.Currency_Gateway_API.model.dto.response.CurrencyFixerDTO;
import com.example.Currency_Gateway_API.model.entity.CurrencyRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    default List<CurrencyRate> toCurrencyRateList(CurrencyFixerDTO currencyFixerDTO) {
        return currencyFixerDTO.getRates().entrySet().stream()
                .map(entry -> toCurrencyRate(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "instantToFormattedString")
    CurrencyDataDTO toDto(CurrencyRate entity);

    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "longToInstant")
    CurrencyRate toEntity(CurrencyDataDTO dto);

    @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
    CurrencyRate toCurrencyRate(String currency, Double rate);

    @Named("instantToFormattedString")
    default String instantToFormattedString(Instant instant) {
        return instant != null
                ? DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneOffset.UTC)
                .format(instant)
                : null;
    }

    @Named("longToInstant")
    default Instant longToInstant(long epochMilli) {
        return Instant.ofEpochMilli(epochMilli);
    }
}