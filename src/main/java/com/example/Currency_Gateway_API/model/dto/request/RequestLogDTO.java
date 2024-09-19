package com.example.Currency_Gateway_API.model.dto.request;

public class RequestLogDTO {

    private Long id;

    private String requestId;

    private String serviceName;

    private String clientId;

    private Long timestamp;

    public RequestLogDTO(String requestId, String serviceName, String clientId, Long timestamp) {
        this.requestId = requestId;
        this.serviceName = serviceName;
        this.clientId = clientId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
