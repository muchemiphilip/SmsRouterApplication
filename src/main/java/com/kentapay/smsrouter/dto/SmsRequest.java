package com.kentapay.smsrouter.dto;

import java.math.BigDecimal;
import java.util.List;

public record SmsRequest(

        long requestRef,

        int clientid,

        String apiKey,

        List<SmsData> data,

        String from,

        String apiSecret,

        BigDecimal totalAmountNeeded

) {
}