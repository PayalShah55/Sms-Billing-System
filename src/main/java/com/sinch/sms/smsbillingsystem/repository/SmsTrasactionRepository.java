package com.sinch.sms.smsbillingsystem.repository;

import com.sinch.sms.smsbillingsystem.model.SmsTransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SmsTrasactionRepository extends JpaRepository<SmsTransactionDetails,Integer> {

    List<SmsTransactionDetails> findByCustomerId(int customerId);
}
