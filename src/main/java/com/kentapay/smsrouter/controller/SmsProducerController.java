package com.kentapay.smsrouter.controller;



import com.kentapay.smsrouter.dto.SmsRequest;
import com.kentapay.smsrouter.service.SmsProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsProducerController {

    private final SmsProducerService smsProducerService;

    public SmsProducerController(
            SmsProducerService smsProducerService
    ) {
        this.smsProducerService = smsProducerService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateSms(
            @RequestParam(defaultValue = "1") int count
    ) {
        if (count < 1 || count > 10) {
            return ResponseEntity.badRequest().body("Count must be between 1 and 10");
        }
        List<SmsRequest> publishedMessages = new ArrayList<>();
        for (int i = 0; i < count; i++) {

            try {
                SmsRequest request = smsProducerService.generateAndPublishSms();
                publishedMessages.add(request);
            } catch (Exception exception) {

                return ResponseEntity.internalServerError().body("Failed to publish SMS: " + exception.getMessage());
            }
        }

        return ResponseEntity.ok(publishedMessages);
    }
}