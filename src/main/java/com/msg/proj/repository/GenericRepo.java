package com.msg.proj.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericRepo<T, K> implements IRepo<T, K> {

    private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("mini_proj");
    protected EntityManager em = emFactory.createEntityManager();

    @Override
    public void create(T entity) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public T read(Class<T> clazz, K id) {
        return em.find(clazz, id);
    }

    @Override
    public void update(T entity) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public List<T> readAll(Class<T> clazz) {
        CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(clazz);
        criteria.select(criteria.from(clazz));
        return em.createQuery(criteria).getResultList();
    }

    public void closeEm() {
        if(em.isOpen()) {
            em.close();
        }
        if(emFactory.isOpen()) {
            emFactory.close();
        }
    }
}
