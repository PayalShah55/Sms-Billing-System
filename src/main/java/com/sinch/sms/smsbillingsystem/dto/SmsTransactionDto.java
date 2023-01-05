package com.sinch.sms.smsbillingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class SmsTransactionDto {

    private int customerId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime smsSentDate;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getSmsSentDate() {
        return smsSentDate;
    }

    public void setSmsSentDate(LocalDateTime smsSentDate) {
        this.smsSentDate = smsSentDate;
    }
}
