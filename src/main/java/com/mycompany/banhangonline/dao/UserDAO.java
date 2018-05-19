package com.mycompany.banhangonline.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("ProjectService");
        em = emf.createEntityManager();
    }
}
