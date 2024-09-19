package com.example.Currency_Gateway_API.model.dto.request.xml;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GetRequestDTO {

    @JacksonXmlProperty(isAttribute = true)
    @NotBlank(message = "Consumer ID is mandatory")
    private String consumer;

    @JacksonXmlProperty
    @NotNull(message = "Currency is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter ISO code")
    private String currency;

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
