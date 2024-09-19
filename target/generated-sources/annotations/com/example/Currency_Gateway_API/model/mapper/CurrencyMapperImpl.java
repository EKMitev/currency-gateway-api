package com.example.Currency_Gateway_API.model.mapper;

import com.example.Currency_Gateway_API.model.dto.response.CurrencyDataDTO;
import com.example.Currency_Gateway_API.model.entity.CurrencyRate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T12:25:56+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyDataDTO toDto(CurrencyRate entity) {
        if ( entity == null ) {
            return null;
        }

        CurrencyDataDTO currencyDataDTO = new CurrencyDataDTO();

        currencyDataDTO.setTimestamp( instantToFormattedString( entity.getTimestamp() ) );
        currencyDataDTO.setId( entity.getId() );
        currencyDataDTO.setCurrency( entity.getCurrency() );
        currencyDataDTO.setRate( entity.getRate() );

        return currencyDataDTO;
    }

    @Override
    public CurrencyRate toEntity(CurrencyDataDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CurrencyRate currencyRate = new CurrencyRate();

        if ( dto.getTimestamp() != null ) {
            currencyRate.setTimestamp( longToInstant( Long.parseLong( dto.getTimestamp() ) ) );
        }
        currencyRate.setId( dto.getId() );
        currencyRate.setCurrency( dto.getCurrency() );
        currencyRate.setRate( dto.getRate() );

        return currencyRate;
    }

    @Override
    public CurrencyRate toCurrencyRate(String currency, Double rate) {
        if ( currency == null && rate == null ) {
            return null;
        }

        CurrencyRate currencyRate = new CurrencyRate();

        currencyRate.setCurrency( currency );
        currencyRate.setRate( rate );
        currencyRate.setTimestamp( java.time.Instant.now() );

        return currencyRate;
    }
}
