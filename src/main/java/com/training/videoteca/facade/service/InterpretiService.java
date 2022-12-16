package com.training.videoteca.facade.service;

import com.training.videoteca.entity.Film;
import com.training.videoteca.entity.Interprete;

import java.util.List;


public interface InterpretiService {

    public List<Interprete> getAll();

    public Interprete findById(Long id);

    public List<Interprete> findByNomeContains(String nome);

    public List<Interprete> findByCognomeContains(String nome);

    public Interprete save(Interprete interprete);

    public List<Interprete> findByNomeAndCognomeContaining(String nome,String cognome);

    public List<Interprete> findByFiltroNomeECognome(String nome, String cognome);

    public Interprete update(Interprete interprete);

}
