package com.sinch.sms.smsbillingsystem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class SmsTransactionDetails {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private int customerId;

    private LocalDateTime smsSentDate;
    @Column(precision = 19, scale = 4)
    private BigDecimal smsPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public BigDecimal getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(BigDecimal smsPrice) {
        this.smsPrice = smsPrice;
    }
}
