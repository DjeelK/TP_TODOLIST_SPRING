package com.m2i.cda.tp_todolist.service;

import com.m2i.cda.tp_todolist.entity.Tache;

import java.util.List;

public interface ITacheService {
    boolean create(Tache p);
    boolean update(Tache p);

    boolean delete(Tache p);

    Tache findById(int id);

    List<Tache> findAll();
}
