package com.mycompany.persistencia;

import com.mycompany.logica.Automovil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class AutomovilJPAController {
    EntityManagerFactory emf;

    public AutomovilJPAController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void create(Automovil automovil) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(automovil);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }

    public void destroy(int id) {
        EntityManager em = emf.createEntityManager();
       try{
           em.getTransaction().begin();
           Automovil automovil=em.getReference(Automovil.class, id);
           em.remove(automovil);
           em.getTransaction().commit();
       }
       finally {
           em.close();
       }
    }
    public void edit(Automovil automovil) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(automovil);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
    }
    public Automovil findAutomovil(int id) {
        EntityManager em = emf.createEntityManager();
       try {
           em.getTransaction().begin();
           Automovil automovil = em.find(Automovil.class, id);
           em.getTransaction().commit();
           return automovil;
       }
       finally {
           em.close();
       }

    }
    public List<Automovil> findAutomoviles() {
        EntityManager em = emf.createEntityManager();
       try {
           return em.createQuery("SELECT a from Automovil a", Automovil.class).getResultList();
       }
       finally {
           em.close();
       }
    }


}
