package com.dirapp.java_dasar_presistence_api_orm_jpa.entity;

import java.time.LocalDateTime;

public interface UpdatedAtAware {

    void setUpdatedAt(LocalDateTime updatedAt);
}
