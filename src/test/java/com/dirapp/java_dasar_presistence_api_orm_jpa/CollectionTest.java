package com.dirapp.java_dasar_presistence_api_orm_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import com.dirapp.java_dasar_presistence_api_orm_jpa.entity.Member;
import com.dirapp.java_dasar_presistence_api_orm_jpa.entity.Name;
import com.dirapp.java_dasar_presistence_api_orm_jpa.util.JpaUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setFirstName("Dira2");
        name.setMiddleName("Sanjaya");
        name.setLastName("Wardana");

        Member member = new Member();
        member.setEmail("test@example.com");
        member.setName(name);

        member.setHobbies(new ArrayList<>());
        member.getHobbies().add("Coding");
        member.getHobbies().add("Gaming");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);

        member.getHobbies().add("Traveling");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateSkills() {
    EntityManagerFactory entityManagerFactory =
    JpaUtil.getEntityManagerFactory();
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();

    Member member = entityManager.find(Member.class, 2);
    member.setSkills(new HashMap<>());
    member.getSkills().put("Java", 90);
    member.getSkills().put("Golang", 90);
    member.getSkills().put("PHP", 85);

    entityManager.merge(member);

    entityTransaction.commit();
    entityManager.close();
    }
}
