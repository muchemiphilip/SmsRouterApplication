package com.kentapay.smsrouter.service;


import com.kentapay.smsrouter.dto.SmsData;
import com.kentapay.smsrouter.dto.SmsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
public class SmsGeneratorService {

    private final SecureRandom random = new SecureRandom();

    @Value("${app.sms.client-id}")
    private int clientId;

    @Value("${app.sms.api-key}")
    private String apiKey;

    @Value("${app.sms.api-secret}")
    private String apiSecret;

    @Value("${app.sms.sender-id}")
    private String senderId;

    @Value("${app.sms.test-recipient}")
    private String recipient;

    private final List<String> messages = List.of(
            "QA and Sec Test 2",
            "Kafka SMS Integration Test",
            "SMS Producer Verification",
            "KentaPay SMS Gateway Test",
            "Africa's Talking Integration Test"
    );

    public SmsRequest generateSmsRequest() {

        long requestRef = generateReference();
        long messageId = generateReference();

        String transactionId = UUID.randomUUID().toString().replace("-", "").substring(0, 12);

        String message = messages.get(random.nextInt(messages.size()));

        SmsData smsData = new SmsData(messageId, recipient, message, transactionId);

        return new SmsRequest(requestRef, clientId, apiKey, List.of(smsData), senderId, apiSecret, BigDecimal.ONE);
    }

    private long generateReference() {

        return Math.abs(random.nextLong() >>> 1);
    }
}