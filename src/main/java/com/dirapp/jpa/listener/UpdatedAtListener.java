package com.dirapp.jpa.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

import com.dirapp.jpa.entity.UpdatedAtAware;

public class UpdatedAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdatedAt(UpdatedAtAware object){
        object.setUpdatedAt(LocalDateTime.now());
    }

}
