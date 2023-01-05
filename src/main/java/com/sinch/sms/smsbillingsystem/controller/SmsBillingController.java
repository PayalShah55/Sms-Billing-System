package com.sinch.sms.smsbillingsystem.controller;

import com.sinch.sms.smsbillingsystem.dto.SmsTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sinch.sms.smsbillingsystem.service.SmsBillingService;

import java.math.BigDecimal;

@RestController
@RequestMapping(path="/sms")
public class SmsBillingController {

    @Autowired
    public SmsBillingService smsBillingService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsTransactionDto smsTransactionDto)
    {
        String result=smsBillingService.sendSms(smsTransactionDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/dueAmountforCurrentMonth/{customerId}")
    public ResponseEntity<String> smsDueAmountforCurrentMonth(@PathVariable Integer customerId)
    {
        BigDecimal billingAmount=smsBillingService.smsDueAmountforCurrentMonth(customerId);
        return new ResponseEntity<>("Billing Amount for customerId " + customerId + " Current Month is: " + billingAmount,HttpStatus.OK);
    }
}
