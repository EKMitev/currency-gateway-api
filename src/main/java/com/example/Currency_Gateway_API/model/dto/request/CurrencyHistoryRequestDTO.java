package com.example.Currency_Gateway_API.model.dto.request;

import com.example.Currency_Gateway_API.model.validation.UniqueRequestId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CurrencyHistoryRequestDTO {

    @NotBlank(message = "Request ID cannot be blank")
    @UniqueRequestId
    private String requestId;

    @NotNull(message = "Timestamp is mandatory")
    private Long timestamp;

    @NotBlank(message = "Client is mandatory")
    private String client;

    @NotNull(message = "Currency is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter ISO code")
    private String currency;

    @NotNull(message = "Period is required")
    private int period;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
