package com.sinch.sms.smsbillingsystem;

import com.sinch.sms.smsbillingsystem.dto.SmsTransactionDto;
import com.sinch.sms.smsbillingsystem.model.Customer;
import com.sinch.sms.smsbillingsystem.model.SmsPlan;
import com.sinch.sms.smsbillingsystem.model.SmsTransactionDetails;
import com.sinch.sms.smsbillingsystem.repository.CustomerRepository;
import com.sinch.sms.smsbillingsystem.repository.SmsTrasactionRepository;
import com.sinch.sms.smsbillingsystem.service.SmsBillingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class JunitSmsBillingServiceTest {

    @Mock
    private SmsTrasactionRepository smsTrasactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    SmsBillingService smsBillingService;

    @Test
    public void sendSms()
    {
        SmsTransactionDto smsTransactionDto=new SmsTransactionDto();
        smsTransactionDto.setCustomerId(101);
        smsTransactionDto.setSmsSentDate(LocalDateTime.now());

        List<SmsTransactionDetails> smsTransactionDetailsList=new ArrayList();
        SmsTransactionDetails smsTransactionDetails=new SmsTransactionDetails();
        smsTransactionDetails.setId(1);
        smsTransactionDetails.setCustomerId(101);
        smsTransactionDetails.setSmsPrice(new BigDecimal(0.0001));
        smsTransactionDetails.setSmsSentDate(LocalDateTime.now());

        SmsTransactionDetails smsTransactionDetails2=new SmsTransactionDetails();
        smsTransactionDetails.setId(1);
        smsTransactionDetails.setCustomerId(101);
        smsTransactionDetails.setSmsPrice(new BigDecimal(0.0001));
        smsTransactionDetails.setSmsSentDate(LocalDateTime.now());

        smsTransactionDetailsList.add(smsTransactionDetails);
        smsTransactionDetailsList.add(smsTransactionDetails2);

        Mockito.when(smsTrasactionRepository.findByCustomerId(Mockito.anyInt())).thenReturn(smsTransactionDetailsList);

        Customer customer=new Customer();
        customer.setId(101);
        customer.setName("Bank");
        SmsPlan smsplan=new SmsPlan();
        smsplan.setId(1l);
        smsplan.setName("Basic");
        smsplan.setFreeMessages(0);
        smsplan.setPricePerMessaage(new BigDecimal("0.0001"));
        customer.setPlan(smsplan);
        //Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));

        smsBillingService.sendSms(smsTransactionDto);
        Assert.assertEquals(new BigDecimal(0.0001).toString(),smsTransactionDetails.getSmsPrice().toString());
    }

    @Test
    public void smsDueAmountforCurrentMonthTest()
    {
        List<SmsTransactionDetails> smsTransactionDetailsList=new ArrayList();
        SmsTransactionDetails smsTransactionDetails=new SmsTransactionDetails();
        smsTransactionDetails.setId(1);
        smsTransactionDetails.setCustomerId(101);
        smsTransactionDetails.setSmsPrice(new BigDecimal(0.0001));
        smsTransactionDetails.setSmsSentDate(LocalDateTime.now());

        SmsTransactionDetails smsTransactionDetails2=new SmsTransactionDetails();
        smsTransactionDetails2.setId(2);
        smsTransactionDetails2.setCustomerId(101);
        smsTransactionDetails2.setSmsPrice(new BigDecimal(0.0001));
        smsTransactionDetails2.setSmsSentDate(LocalDateTime.now());

        smsTransactionDetailsList.add(smsTransactionDetails);
        smsTransactionDetailsList.add(smsTransactionDetails2);

        Mockito.when(smsTrasactionRepository.findByCustomerId(Mockito.anyInt())).thenReturn(smsTransactionDetailsList);

        BigDecimal billingAmount=smsBillingService.smsDueAmountforCurrentMonth(101);
        Assert.assertEquals(new BigDecimal(0.0002).setScale(4, BigDecimal.ROUND_HALF_UP).toString(),billingAmount.toString());
    }
}
