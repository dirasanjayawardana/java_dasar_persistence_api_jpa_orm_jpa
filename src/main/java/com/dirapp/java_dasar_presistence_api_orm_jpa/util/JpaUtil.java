package com.dirapp.java_dasar_presistence_api_orm_jpa.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// membuat singleton EntityManagerFactory, hanya dibuat sekali, jika sudah pernah dibuat, gunakan object yg sama
public class JpaUtil {

  private static EntityManagerFactory entityManagerFactory = null;

  public static EntityManagerFactory getEntityManagerFactory() {
    if (entityManagerFactory == null) {
      entityManagerFactory = Persistence.createEntityManagerFactory("BELAJAR");
    }
    return entityManagerFactory;
  }
}