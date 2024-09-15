package com.dirapp.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.dirapp.jpa.entity.Category;
import com.dirapp.jpa.util.JpaUtil;

public class GeneratedValueTest {

    @Test
    void generatedValue() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Gadget");
        category.setDescription("Gadget Termurah");
        entityManager.persist(category);

        Assertions.assertNotNull(category.getId());

        entityTransaction.commit();
        entityManager.close();
    }
}
