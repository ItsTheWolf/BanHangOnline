package com.mycompany.showroomonline.dao;

import com.mycompany.showroomonline.dto.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
    }

    public List<User> readAll() {
        em.clear();
        em.getTransaction().begin();
        List<User> result = em.createQuery("FROM User", User.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void createUser(User obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public User read(String username) {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        User obj = em.find(User.class, username);
        em.getTransaction().commit();
        return obj;
    }

    public void updateUser(User obj) {
        User curr = em.find(User.class, obj.getUsername());
        em.getTransaction().begin();
        curr.setFullname(obj.getFullname());
        curr.setEmail(obj.getEmail());
        curr.setAddress(obj.getAddress());
        curr.setRole(obj.getRole());
        em.getTransaction().commit();
    }

    public void updateUserPassword(User obj) {
        User curr = em.find(User.class, obj.getUsername());
        em.getTransaction().begin();
        curr.setPassword(obj.getPassword());
        em.getTransaction().commit();
    }

    public void deleteUser(String username) {
        User curr = em.find(User.class, username);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }

    public void resetAI() {
        em.getTransaction().begin();
        em.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
}
