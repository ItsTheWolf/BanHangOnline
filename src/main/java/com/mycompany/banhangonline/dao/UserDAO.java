package com.mycompany.banhangonline.dao;

import com.mycompany.banhangonline.dto.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("BanHangService");
        em = emf.createEntityManager();
    }

    public List<User> readAll() {
        em.clear();
        em.getTransaction().begin();
        List<User> result = em.createQuery("FROM product", User.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void createUser(User obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public User read(int id) {
        emf = Persistence.createEntityManagerFactory("BanHangService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        User obj = em.find(User.class, id);
        em.getTransaction().commit();
        return obj;
    }

    public void updateUser(User obj) {
        User curr = em.find(User.class, obj.getId());
        em.getTransaction().begin();
        curr.setUsername(obj.getUsername());
        curr.setPassword(obj.getPassword());
        curr.setFullname(obj.getFullname());
        curr.setEmail(obj.getEmail());
        curr.setAddress(obj.getAddress());
        curr.setBirthday(obj.getBirthday());
        curr.setRole(obj.getRole());
        em.getTransaction().commit();
    }

    public void deleteUser(int id) {
        User curr = em.find(User.class, id);
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
