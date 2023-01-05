package com.sinch.sms.smsbillingsystem.service;

import com.sinch.sms.smsbillingsystem.dto.SmsTransactionDto;

import com.sinch.sms.smsbillingsystem.model.Customer;
import com.sinch.sms.smsbillingsystem.model.SmsTransactionDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinch.sms.smsbillingsystem.repository.CustomerRepository;
import com.sinch.sms.smsbillingsystem.repository.SmsTrasactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class SmsBillingService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SmsTrasactionRepository smsTrasactionRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public String sendSms(SmsTransactionDto smsTransactionDto) {

        LOGGER.info("inside sendSms");
        BigDecimal smsPrice=null;
        try {
                SmsTransactionDetails smsTransactionDetails=new SmsTransactionDetails();
                smsTransactionDetails.setCustomerId(smsTransactionDto.getCustomerId());
                smsTransactionDetails.setSmsSentDate(smsTransactionDto.getSmsSentDate());
                smsPrice=getPriceForMessageSent(smsTransactionDto.getCustomerId());
                smsTransactionDetails.setSmsPrice(smsPrice);

                smsTrasactionRepository.save(smsTransactionDetails);
        }
        catch(Exception e)
        {
            LOGGER.info("some exception occurred");
            return "Some Exception occurred while sending messages";
        }
        return "Message sent by customer: " + smsTransactionDto.getCustomerId() + " price incurred is-" + smsPrice .setScale(4)+"USD";
    }

    private List<SmsTransactionDetails> getSmsTransactionDetailsForCurrentMonth(int customerId)
    {
        LOGGER.info("inside getSmsTransactionDetailsForCurrentMonth");
        List<SmsTransactionDetails> smsTransactionDetailsList=smsTrasactionRepository.findByCustomerId(customerId);
        if(smsTransactionDetailsList!=null && smsTransactionDetailsList.size()>0)
            smsTransactionDetailsList=smsTransactionDetailsList.stream().filter(smsTransactionDetails -> smsTransactionDetails.getSmsSentDate().getMonth()== LocalDateTime.now().getMonth()).collect(Collectors.toList());

        return smsTransactionDetailsList;
    }

    private BigDecimal getPriceForMessageSent(int customerId)
    {
        LOGGER.info("inside getPriceForMessageSent");
        int smsCountforCurrentMonth=0;
        BigDecimal smsPrice=BigDecimal.ZERO;
        List<SmsTransactionDetails> smsTransactionDetailsList=getSmsTransactionDetailsForCurrentMonth(customerId);

        if(smsTransactionDetailsList!=null && !smsTransactionDetailsList.isEmpty())
            smsCountforCurrentMonth=(int)smsTransactionDetailsList.stream().filter(smsTransactionDetails -> smsTransactionDetails.getSmsSentDate().getMonth()== LocalDateTime.now().getMonth()).count();

        Optional<Customer> customer=customerRepository.findById(customerId);
        if(customer.isPresent()) {

            if(customer.get().getPlan().getId()==3 && smsCountforCurrentMonth>=4)
                smsPrice=new BigDecimal(0.0005);
            else
                smsPrice=(smsCountforCurrentMonth<customer.get().getPlan().getFreeMessages()) ? BigDecimal.ZERO :customer.get().getPlan().getPricePerMessaage();

        }

        return smsPrice.setScale(4, BigDecimal.ROUND_HALF_UP);
    }


    public BigDecimal smsDueAmountforCurrentMonth(Integer customerId) {

        LOGGER.info("inside smsDueAmountforCurrentMonth");
        List<SmsTransactionDetails> smsTransactionDetailsList=getSmsTransactionDetailsForCurrentMonth(customerId);
        BigDecimal currentBillingAmount=BigDecimal.ZERO;
        if(smsTransactionDetailsList!=null && !smsTransactionDetailsList.isEmpty()) {
             currentBillingAmount = smsTransactionDetailsList.stream().map(SmsTransactionDetails -> SmsTransactionDetails.getSmsPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
            return currentBillingAmount.setScale(4, BigDecimal.ROUND_HALF_UP);
    }
}
