package com.dirapp.java_dasar_presistence_api_orm_jpa.entity.relations.is_a;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Employee{

    @Column(name = "total_employee")
    private Integer totalEmployee;

    public Integer getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(Integer totalEmployee) {
        this.totalEmployee = totalEmployee;
    }
}