package com.dirapp.java_dasar_presistence_api_orm_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import com.dirapp.java_dasar_presistence_api_orm_jpa.entity.Category;
import com.dirapp.java_dasar_presistence_api_orm_jpa.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DateTest {

  @Test
  void dateAndTime() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Category category = new Category();
    category.setName("Food");
    category.setCreatedAt(Calendar.getInstance());
    category.setUpdatedAt(LocalDateTime.now());

    entityManager.persist(category);

    entityTransaction.commit();
    entityManager.close();
  }
}
