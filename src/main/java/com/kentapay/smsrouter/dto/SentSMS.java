package com.kentapay.smsrouter.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_SENT_SMSES", schema = "dbo")
public class SentSMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sent_sms_id")
    private long sentSmsId;

    @Column(name = "tenant_id")
    private int tenantId;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_processed")
    private Date dateProcessed;

    @Column(name = "status")
    private int status;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "response_desc")
    private String responseDesc;

    @Column(name = "batch_id")
    private long batchId;

    @Column(name = "message_id")
    private long messageId;

    @Column(name = "callback_status")
    private String callbackStatus;

    @Column(name = "number_of_retries")
    private int numberOfRetries;

    @Column(name = "message_parts")
    private int messageParts;

    @Column(name = "external_ref")
    private String externalRef;

    @Column(name = "units")
    private BigDecimal units;

    @Column(name = "channel")
    private String channel;

    // Getters and setters

    public long getSentSmsId() {
        return sentSmsId;
    }

    public void setSentSmsId(long sentSmsId) {
        this.sentSmsId = sentSmsId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getCallbackStatus() {
        return callbackStatus;
    }

    public void setCallbackStatus(String callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    public int getNumberOfRetries() {
        return numberOfRetries;
    }

    public void setNumberOfRetries(int numberOfRetries) {
        this.numberOfRetries = numberOfRetries;
    }

    public int getMessageParts() {
        return messageParts;
    }

    public void setMessageParts(int messageParts) {
        this.messageParts = messageParts;
    }

    public String getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(String externalRef) {
        this.externalRef = externalRef;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}