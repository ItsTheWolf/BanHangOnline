/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banhangonline.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author danie
 */
public class RoleDAO {

    private EntityManager em;
    private EntityManagerFactory emf;

    public RoleDAO() {
        emf = Persistence.createEntityManagerFactory("ProjectService");
        em = emf.createEntityManager();
    }
}
