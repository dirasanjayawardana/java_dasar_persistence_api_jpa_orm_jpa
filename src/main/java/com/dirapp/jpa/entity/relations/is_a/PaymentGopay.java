package com.dirapp.jpa.entity.relations.is_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments_gopay")
public class PaymentGopay extends Payment{

    @Column(name = "gopay_id")
    private String gopayId;

    public String getGopayId() {
        return gopayId;
    }

    public void setGopayId(String gopayId) {
        this.gopayId = gopayId;
    }
}
