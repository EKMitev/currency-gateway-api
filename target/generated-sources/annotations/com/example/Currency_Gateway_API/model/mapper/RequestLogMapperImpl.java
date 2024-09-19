package com.example.Currency_Gateway_API.model.mapper;

import com.example.Currency_Gateway_API.model.dto.request.RequestLogDTO;
import com.example.Currency_Gateway_API.model.entity.RequestLog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-18T16:53:05+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class RequestLogMapperImpl implements RequestLogMapper {

    @Override
    public RequestLog toRequestLog(RequestLogDTO requestLogDTO) {
        if ( requestLogDTO == null ) {
            return null;
        }

        RequestLog requestLog = new RequestLog();

        requestLog.setRequestTime( RequestLogMapper.mapTimestampToInstant( requestLogDTO.getTimestamp() ) );
        requestLog.setId( requestLogDTO.getId() );
        requestLog.setRequestId( requestLogDTO.getRequestId() );
        requestLog.setServiceName( requestLogDTO.getServiceName() );
        requestLog.setClientId( requestLogDTO.getClientId() );

        return requestLog;
    }
}
