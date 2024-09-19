package com.example.Currency_Gateway_API.model.mapper;

import com.example.Currency_Gateway_API.model.dto.request.RequestLogDTO;
import com.example.Currency_Gateway_API.model.entity.RequestLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface RequestLogMapper {

    @Mapping(source = "timestamp", target = "requestTime", qualifiedByName = "mapTimestampToInstant")
    RequestLog toRequestLog(RequestLogDTO requestLogDTO);

    @Named("mapTimestampToInstant")
    static Instant mapTimestampToInstant(Long timestamp) {
        return timestamp != null ? Instant.ofEpochMilli(timestamp) : null;
    }
}
