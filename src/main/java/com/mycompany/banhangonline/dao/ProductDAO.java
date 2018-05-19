package com.mycompany.banhangonline.dao;

import com.mycompany.banhangonline.dto.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public ProductDAO() {
        emf = Persistence.createEntityManagerFactory("BanHangService");
        em = emf.createEntityManager();
    }

    public List<Product> readAll() {
        em.clear();
        em.getTransaction().begin();
        List<Product> result = em.createQuery("FROM product", Product.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void createProduct(Product obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public Product read(int id) {
        emf = Persistence.createEntityManagerFactory("BanHangService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Product obj = em.find(Product.class, id);
        em.getTransaction().commit();
        return obj;
    }

    public void updateProduct(Product obj) {
        Product curr = em.find(Product.class, obj.getId());
        em.getTransaction().begin();
        curr.setName(obj.getName());
        curr.setDescription(obj.getDescription());
        curr.setPrice(obj.getPrice());
        curr.setAmount(obj.getAmount());
        curr.setThumnail(obj.getThumnail());
        curr.setCategory(obj.getCategory());
        em.getTransaction().commit();
    }

    public void deleteProduct(int id) {
        Product curr = em.find(Product.class, id);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }

    public void resetAI() {
        em.getTransaction().begin();
        em.createNativeQuery("ALTER TABLE products AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
}
