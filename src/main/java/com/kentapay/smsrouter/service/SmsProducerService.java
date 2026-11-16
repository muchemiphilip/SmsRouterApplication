package com.kentapay.smsrouter.service;

import com.kentapay.smsrouter.dto.SentSMS;
import com.kentapay.smsrouter.dto.SmsData;
import com.kentapay.smsrouter.dto.SmsRequest;
import com.kentapay.smsrouter.repository.SentSMSRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class SmsProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final SentSMSRepository sentSMSRepository;

    private final SmsGeneratorService smsGeneratorService;

    @Value("${app.kafka.topic}")
    private String topic;

    @Value("${app.sms.tenant-id}")
    private int tenantId;

    @Value("${app.sms.provider-id}")
    private Long providerId;

    @Value("${app.sms.sender-id}")
    private String senderId;

    @Value("${app.sms.pending-status}")
    private int pendingStatus;

    @Value("${app.sms.channel}")
    private String channel;

    public SmsProducerService(
            KafkaTemplate<String, Object> kafkaTemplate,
            SentSMSRepository sentSMSRepository,
            SmsGeneratorService smsGeneratorService
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.sentSMSRepository = sentSMSRepository;
        this.smsGeneratorService = smsGeneratorService;
    }

    public SmsRequest generateAndPublishSms() throws Exception {

        // 1. Generate SMS request
        SmsRequest request = smsGeneratorService.generateSmsRequest();

        // 2. Extract SMS data
        SmsData smsData = request.data().get(0);

        // 3. Prepare database entity
        SentSMS sentSMS = new SentSMS();
        sentSMS.setTenantId(tenantId);
        sentSMS.setSenderId(senderId);
        sentSMS.setProviderId(providerId);
        sentSMS.setTransactionId(smsData.transactionID());
        sentSMS.setMsisdn(smsData.to());
        sentSMS.setMessage(smsData.message());
        sentSMS.setStatus(pendingStatus);
        sentSMS.setResponseCode("0");
        sentSMS.setResponseDesc("PENDING");
        sentSMS.setBatchId(request.requestRef());
        sentSMS.setMessageId(smsData.messageId());
        sentSMS.setCallbackStatus("PENDING");
        sentSMS.setNumberOfRetries(0);
        sentSMS.setMessageParts(1);
        sentSMS.setExternalRef(String.valueOf(request.requestRef()));
        sentSMS.setUnits(BigDecimal.ONE);
        sentSMS.setChannel(channel);

        // 4. Persist SMS in SQL Server first
        SentSMS savedSMS = sentSMSRepository.saveAndFlush(sentSMS);

        // 5. Publish only after successful persistence
        try {
            kafkaTemplate.send(topic, String.valueOf(request.requestRef()), request).get(10, TimeUnit.SECONDS);
            System.out.println("SMS successfully published to Kafka. Database ID: " + savedSMS.getSentSmsId());
            return request;

        } catch (Exception exception) {

            // Database record remains PENDING.
            // Do not delete or mark it as successfully processed.

            System.err.println("Kafka publishing failed for SMS ID: " + savedSMS.getSentSmsId());
            throw new RuntimeException("SMS persisted but Kafka publishing failed", exception);
        }
    }
}