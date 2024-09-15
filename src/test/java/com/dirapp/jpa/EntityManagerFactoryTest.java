package com.dirapp.jpa;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.dirapp.jpa.util.JpaUtil;

public class EntityManagerFactoryTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        Assertions.assertNotNull(entityManagerFactory);
    }
}