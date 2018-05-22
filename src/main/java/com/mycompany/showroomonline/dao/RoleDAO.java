package com.mycompany.showroomonline.dao;

import com.mycompany.showroomonline.dto.Role;
import com.mycompany.showroomonline.dto.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RoleDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public RoleDAO() {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
    }

    public List<Role> readAll() {
        em.clear();
        em.getTransaction().begin();
        List<Role> result = em.createQuery("FROM Role", Role.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public void createRole(Role obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    public Role read(int id) {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Role obj = em.find(Role.class, id);
        em.getTransaction().commit();
        return obj;
    }

    public void updateRole(Role obj) {
        Role curr = em.find(Role.class, obj.getId());
        em.getTransaction().begin();
        curr.setName(obj.getName());
        em.getTransaction().commit();
    }

    public void deleteRole(int id) {
        Role curr = em.find(Role.class, id);
        em.getTransaction().begin();
        em.remove(curr);
        em.getTransaction().commit();
    }
    
    public List<User> findbyRole(int id) {
        emf = Persistence.createEntityManagerFactory("ShowroomService");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<User> result = em.createQuery("FROM User WHERE roleid=" + id).getResultList();
        em.getTransaction().commit();
        return result;
    }
    
    public void resetAI(int i) {
        em.getTransaction().begin();
        em.createNativeQuery("ALTER TABLE Roles AUTO_INCREMENT = " + i).executeUpdate();
        em.getTransaction().commit();
    }
}
