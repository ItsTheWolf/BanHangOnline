package com.mycompany.banhangonline.dao;

import com.mycompany.banhangonline.dto.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public CategoryDAO() {
        emf = Persistence.createEntityManagerFactory("BanHangService");
        em = emf.createEntityManager();
    }

    public List<Category> readAll() {
        em.clear();
        em.getTransaction().begin();
        List<Category> result = em.createQuery("FROM category", Category.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void createCategory(Category obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public Category read(int id) {
        emf = Persistence.createEntityManagerFactory("BanHangService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Category obj = em.find(Category.class, id);
        em.getTransaction().commit();
        return obj;
    }

    public void updateCategory(Category obj) {
        Category curr = em.find(Category.class, obj.getId());
        em.getTransaction().begin();
        curr.setName(obj.getName());
        em.getTransaction().commit();
    }

    public void deleteCategory(int id) {
        Category curr = em.find(Category.class, id);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }
    
    public void resetAI() {
        em.getTransaction().begin();
        em.createNativeQuery("ALTER TABLE categories AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
}
