package com.kentapay.smsrouter.dto;

public record SmsData(

        long messageId,

        String to,

        String message,

        String transactionID

) {
}