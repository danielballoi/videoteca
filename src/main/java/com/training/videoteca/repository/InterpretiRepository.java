package com.training.videoteca.repository;

import com.training.videoteca.entity.Interprete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterpretiRepository extends JpaRepository<Interprete, Long> {
    List<Interprete> findAll();

    List<Interprete> findByNomeContains(String nome);

    List<Interprete> findByCognomeContains(String cognome);

    Interprete save(Interprete interprete);

    List<Interprete> findByNomeAndCognomeContains(String nome, String cognome);

    List<Interprete> findByNomeContainsIgnoreCaseOrCognomeIgnoreCaseContainsOrderByCognomeAscNomeAsc(String nome,String cognome);

}
