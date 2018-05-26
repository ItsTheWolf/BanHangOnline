package com.mycompany.showroomonline.dao;

import com.mycompany.showroomonline.dto.Thumbnail;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ThumbnailDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public ThumbnailDAO() {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
    }

    public List<Thumbnail> readAll() {
        em.clear();
        em.getTransaction().begin();
        List<Thumbnail> result = em.createQuery("FROM Thumbnail", Thumbnail.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void createThumbnail(Thumbnail obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public Thumbnail read(int id) {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Thumbnail obj = em.find(Thumbnail.class, id);
        em.getTransaction().commit();
        return obj;
    }

    public void updateThumbnail(Thumbnail obj) {
        Thumbnail curr = em.find(Thumbnail.class, obj.getId());
        em.getTransaction().begin();
        curr.setId(obj.getId());
        curr.setName(obj.getName());
        curr.setLink(obj.getLink());
        em.getTransaction().commit();
    }

    public void deleteThumbnail(int id) {
        Thumbnail curr = em.find(Thumbnail.class, id);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }

    public void resetAI() {
        em.getTransaction().begin();
        em.createNativeQuery("ALTER TABLE thumbnails AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
}
