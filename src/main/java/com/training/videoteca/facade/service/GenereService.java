package com.training.videoteca.facade.service;

import com.training.videoteca.entity.Genere;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GenereService {

    public List<Genere> getAll();

    public Genere getGenereById(Long id);

    public Genere findByDescrizioneContaining(String descrizione);

    public Genere postGenereOnDB(Genere genere);
}
