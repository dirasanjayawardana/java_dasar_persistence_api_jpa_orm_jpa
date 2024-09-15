package com.dirapp.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import com.dirapp.jpa.entity.Customer;
import com.dirapp.jpa.util.JpaUtil;

public class ColumnTest {

    @Test
    void column() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("Dira");
        customer.setPrimaryEmail("contoh@example.com");
        customer.setFullName("Dira Sanjaya Wardana");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void transientTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("3");
        customer.setName("Dira");
        customer.setPrimaryEmail("contoh@example.com");
        customer.setFullName("Dira Sanjaya Wardana");

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
