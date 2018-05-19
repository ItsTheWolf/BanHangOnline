
package com.mycompany.banhangonline.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RoleDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public RoleDAO() {
        emf = Persistence.createEntityManagerFactory("ProjectService");
        em = emf.createEntityManager();
    }
}
