package com.sinch.sms.smsbillingsystem.repository;

import com.sinch.sms.smsbillingsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
