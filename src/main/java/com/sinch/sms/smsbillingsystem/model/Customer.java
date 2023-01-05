package com.sinch.sms.smsbillingsystem.model;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="plan_id",referencedColumnName = "id")
    private SmsPlan plan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SmsPlan getPlan() {
        return plan;
    }

    public void setPlan(SmsPlan plan) {
        this.plan = plan;
    }

}
