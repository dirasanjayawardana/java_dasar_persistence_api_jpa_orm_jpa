package com.dirapp.java_dasar_presistence_api_orm_jpa.entity.relations.is_a;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions_debit")
public class TransactionDebit extends Transaction{

    @Column(name = "debit_amount")
    private Long debitAmount;

    public Long getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(Long debitAmount) {
        this.debitAmount = debitAmount;
    }
}