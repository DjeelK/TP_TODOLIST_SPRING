package com.m2i.cda.tp_todolist.service.impl;

import com.m2i.cda.tp_todolist.entity.Tache;
import com.m2i.cda.tp_todolist.service.ITacheService;
import com.m2i.cda.tp_todolist.utils.ServiceHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService implements ITacheService {

    @Autowired
    private ServiceHibernate serviceHibernate;
    private Session session;

    public TacheService(ServiceHibernate serviceHibernate){
        this.serviceHibernate = serviceHibernate;
        session = this.serviceHibernate.getSession();
    }

    @Override
    public boolean create(Tache p) {
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Tache p) {
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Tache p) {
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Tache findById(int id) {
        Tache tache = null;
        tache = (Tache) session.get(Tache.class,id);
        return tache;
    }

    @Override
    public List<Tache> findAll() {
        Query<Tache> tacheQuery = session.createQuery("from Tache");
        return tacheQuery.list();
    }
}
