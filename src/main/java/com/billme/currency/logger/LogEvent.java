package com.billme.currency.logger;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST_ATTEMPT")
public class RequestAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long   id;

    @Column(name = "TS", nullable = false)
    private long   timestamp;

    @Column(name = "CURRENCY_CODE", nullable = true)
    private String currencyCode;

    @Column(name = "CLIENT_ADDRESS", nullable = true)
    private String clientAddress;

    public RequestAttempt(String currencyCode, String clientAddress) {
        this.timestamp = System.currentTimeMillis();
        this.currencyCode = currencyCode;
        this.clientAddress = clientAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestAttempt that = (RequestAttempt) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RequestLog [id=");
        builder.append(id);
        builder.append(", timestamp=");
        builder.append(Instant.ofEpochMilli(timestamp));
        builder.append(", currencyCode=");
        builder.append(currencyCode);
        builder.append(", clientAddress=");
        builder.append(clientAddress);
        builder.append("]");
        return builder.toString();
    }

}
