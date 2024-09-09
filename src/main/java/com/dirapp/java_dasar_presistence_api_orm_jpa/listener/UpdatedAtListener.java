package com.dirapp.java_dasar_presistence_api_orm_jpa.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

import com.dirapp.java_dasar_presistence_api_orm_jpa.entity.UpdatedAtAware;

public class UpdatedAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdatedAt(UpdatedAtAware object){
        object.setUpdatedAt(LocalDateTime.now());
    }

}
