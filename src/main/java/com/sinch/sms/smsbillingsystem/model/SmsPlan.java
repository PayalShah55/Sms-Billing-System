package com.sinch.sms.smsbillingsystem.model;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "smsplan")
public class SmsPlan {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer freeMessages;
    @Column(precision = 19, scale = 4)
    private BigDecimal pricePerMessaage;

    /*@OneToOne(mappedBy = "plan")
    private Customer customer;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeMessages() {
        return freeMessages;
    }

    public void setFreeMessages(int freeMessages) {
        this.freeMessages = freeMessages;
    }

    public BigDecimal getPricePerMessaage() {
        return pricePerMessaage;
    }

    public void setPricePerMessaage(BigDecimal pricePerMessaage) {
        this.pricePerMessaage = pricePerMessaage;
    }

}
