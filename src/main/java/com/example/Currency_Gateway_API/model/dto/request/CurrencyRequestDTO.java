package com.example.Currency_Gateway_API.model.dto.request;

import com.example.Currency_Gateway_API.model.validation.UniqueRequestId;
import jakarta.validation.constraints.*;

public class CurrencyRequestDTO {

    @NotBlank(message = "Request ID cannot be blank")
    @UniqueRequestId
    private String requestId;

    @NotNull(message = "Timestamp is required")
    private Long timestamp;

    @NotBlank(message = "Client must be provided")
    private String client;

    @NotNull(message = "Currency is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter ISO code")
    private String currency;

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
}
