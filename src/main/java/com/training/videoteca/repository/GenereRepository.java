package com.training.videoteca.repository;

import com.training.videoteca.entity.Genere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenereRepository extends CrudRepository<Genere, Long> {

    public Genere findByDescrizioneContains(String descrizione);


}
