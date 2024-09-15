package com.dirapp.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.dirapp.jpa.entity.Department;
import com.dirapp.jpa.entity.DepartmentId;
import com.dirapp.jpa.entity.Member;
import com.dirapp.jpa.entity.Name;
import com.dirapp.jpa.util.JpaUtil;

public class EmbeddedTest {

    @Test
    void embedded() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr");
        name.setFirstName("Dira");
        name.setMiddleName("Sanjaya");
        name.setLastName("Wardana");

        Member member = new Member();
        member.setEmail("email@example.com");
        member.setName(name);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("dirapp");
        id.setDepartmentId("tech");

        Department department = new Department();
        department.setId(id);
        department.setName("Teknologi");

        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedIdFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        DepartmentId id = new DepartmentId();
        id.setCompanyId("dirapp");
        id.setDepartmentId("tech");

        Department department = entityManager.find(Department.class, id);
        Assertions.assertEquals("Teknologi", department.getName());

        entityTransaction.commit();
        entityManager.close();
    }
}
