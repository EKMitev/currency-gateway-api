package com.example.Currency_Gateway_API.model.dto.request.xml;

import com.example.Currency_Gateway_API.model.validation.UniqueRequestId;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@JacksonXmlRootElement(localName = "command")
public class CommandDTO {

    @JacksonXmlProperty(isAttribute = true)
    @NotBlank(message = "Command ID is mandatory")
    @UniqueRequestId
    private String id;

    @JacksonXmlProperty(isAttribute = true)
    @Valid
    private GetRequestDTO get;

    @JacksonXmlProperty(isAttribute = true)
    @Valid
    private HistoryRequestDTO history;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetRequestDTO getGet() {
        return get;
    }

    public void setGet(GetRequestDTO get) {
        this.get = get;
    }

    public HistoryRequestDTO getHistory() {
        return history;
    }

    public void setHistory(HistoryRequestDTO history) {
        this.history = history;
    }
}
