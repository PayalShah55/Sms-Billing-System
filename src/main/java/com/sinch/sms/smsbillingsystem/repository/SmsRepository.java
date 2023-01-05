package com.sinch.sms.smsbillingsystem.repository;

import com.sinch.sms.smsbillingsystem.model.SmsPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<SmsPlan,Long> {

}
