package com.training.videoteca.controllers;

import com.training.videoteca.POJO.GenerePojo;
import com.training.videoteca.POJO.userResponsePojo;
import com.training.videoteca.entity.Genere;
import com.training.videoteca.facade.GenereFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/generi")
public class GeneriController {

    @Autowired
    GenereFacade genereFacade;

    //gestione errori non integrata in quanto è "concesso" solo il 200
    @GetMapping(value = "/listaGeneri", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Genere>> getGenere() {
        return new ResponseEntity<>(genereFacade.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"genere/{id}", "/genere"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenereById(@PathVariable String id) {
        userResponsePojo userRP = genereFacade.getGenereById(id);
        if (userRP.getErrorResponse().getHttpStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(userRP.getGenere(), userRP.getErrorResponse().getHttpStatus());
        } else
            return new ResponseEntity<>(genereFacade.getGenereById(id), HttpStatus.OK);
    }


    @GetMapping(value = "/descrizione", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByDescrizioneContaining(@RequestParam(name = "descrizione") String descrizione) {
        userResponsePojo userRP = genereFacade.findByDescrizioneContaining(descrizione);
        if (userRP.getErrorResponse().getHttpStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(userRP.getGenere(), userRP.getErrorResponse().getHttpStatus());
        } else {
            return new ResponseEntity<>(userRP.getErrorResponse(), userRP.getErrorResponse().getHttpStatus());
        }
    }

    @PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postGenereOnDB(
            @RequestBody Genere genere){
        userResponsePojo gR = genereFacade.postGenereOnDB(genere);
        if(gR.getErrorResponse().getHttpStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(gR.getGenere(), gR.getErrorResponse().getHttpStatus());
        }else {
            return new ResponseEntity<>(gR.getErrorResponse(),gR.getErrorResponse().getHttpStatus());
        }
    }
}