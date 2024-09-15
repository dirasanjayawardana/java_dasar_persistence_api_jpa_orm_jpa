package com.dirapp.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import com.dirapp.jpa.entity.Customer;
import com.dirapp.jpa.enums.CustomerType;
import com.dirapp.jpa.util.JpaUtil;

public class EnumTest {

    @Test
    void enumTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("4");
        customer.setName("Wardana");
        customer.setAge((byte) 30);
        customer.setMarried(false);
        customer.setType(CustomerType.PREMIUM);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
