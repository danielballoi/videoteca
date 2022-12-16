package com.training.videoteca.facade.serviceImpl;

import com.training.videoteca.entity.Film;
import com.training.videoteca.entity.Interprete;

import com.training.videoteca.repository.InterpretiRepository;
import com.training.videoteca.facade.service.InterpretiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class InterpretiServiceImpl implements InterpretiService {

    @Autowired
 private InterpretiRepository interpretiRepository;

    @Override
    public List<Interprete> getAll() {
        List<Interprete> all = interpretiRepository.findAll();
        return all;
    }

    @Override
    public Interprete findById(Long id) {
        return interpretiRepository.findById(id).orElse(null);
    }

    @Override
    public List<Interprete> findByNomeContains(String nome) {
        return interpretiRepository.findByNomeContains(nome);
    }

    @Override
    public List<Interprete> findByCognomeContains(String cognome) {
        return interpretiRepository.findByCognomeContains(cognome);
    }

    @Override
    public Interprete save(Interprete interprete) {
        return interpretiRepository.save(interprete);
    }

    @Override
    public List<Interprete> findByNomeAndCognomeContaining(String nome, String cognome) {
        return interpretiRepository.findByNomeAndCognomeContains(nome, cognome);
    }

    @Override
    public List<Interprete> findByFiltroNomeECognome(String nome, String cognome) {
        return interpretiRepository.findByNomeContainsIgnoreCaseOrCognomeIgnoreCaseContainsOrderByCognomeAscNomeAsc(nome,cognome);
    }

    @Override
    public Interprete update(Interprete interprete) {
        Interprete salvato = interpretiRepository.save(interprete);
        log.info(salvato.toString());
        return salvato;
    }


}
