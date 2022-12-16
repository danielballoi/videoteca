package com.training.videoteca.facade.serviceImpl;

import com.training.videoteca.facade.service.GenereService;
import com.training.videoteca.entity.Genere;
import com.training.videoteca.repository.GenereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(readOnly=true)   //chiave di sicurezza per il service
public class GenereServiceImpl implements GenereService {

    @Autowired
    GenereRepository genereRepository;

    public List<Genere> getAll(){
        List <Genere> convertiti = new ArrayList<>();
        Iterable<Genere> trovati= genereRepository.findAll();
        for (Genere g :trovati ) {
            convertiti.add(g);
        }
        return convertiti;
    }

    @Override
    public Genere getGenereById(Long id) {
        return  genereRepository.findById(id).orElse(null);
    }

    @Override
    public Genere findByDescrizioneContaining(String descrizione) {
        return genereRepository.findByDescrizioneContains(descrizione);
    }

    @Override
    public Genere postGenereOnDB(Genere genere) {
        return genereRepository.save(genere);
    }


}
