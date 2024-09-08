package com.dirapp.java_dasar_presistence_api_orm_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import com.dirapp.java_dasar_presistence_api_orm_jpa.entity.Image;
import com.dirapp.java_dasar_presistence_api_orm_jpa.util.JpaUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LargeObjectTest {

  @Test
  void largeObject() throws IOException {
    EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Image image = new Image();
    image.setName("Contoh Image");
    image.setDescription("Contoh Deskripsi Image");

    // byte[] bytes = Files.readAllBytes(Path.of(getClass().getResource("/images/sample.png").getPath()));
    byte[] bytes = Files.readAllBytes(Path.of("src/main/resources/images/sample.png"));
    
    image.setImage(bytes);

    entityManager.persist(image);

    entityTransaction.commit();
    entityManager.close();
  }
}
