package com.dirapp.java_dasar_presistence_api_orm_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.dirapp.java_dasar_presistence_api_orm_jpa.util.JpaUtil;

public class TransactionTest {

  @Test
  void transaction() {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    Assertions.assertNotNull(entityTransaction);

    try {
      entityTransaction.begin();

      // manipulasi database ...

      entityTransaction.commit();
    } catch (Throwable throwable) {
      entityTransaction.rollback();
    }

    entityManager.close();
  }
}