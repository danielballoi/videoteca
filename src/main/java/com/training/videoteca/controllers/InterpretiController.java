package com.training.videoteca.controllers;

import com.training.videoteca.POJO.InterpretePojo;
import com.training.videoteca.POJO.InterpreteResponse;
import com.training.videoteca.entity.Interprete;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.facade.InterpretiFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/interpreti")
public class InterpretiController {
    @Autowired
     InterpretiFacade interpretiFacade;

    @GetMapping(value ="/listaInterpreti", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() throws InternalServerErrorException {
        try {
            InterpreteResponse interpreteResponse = interpretiFacade.getAll();
            return new ResponseEntity<>(interpreteResponse.getInterpreteList(), interpreteResponse.getErrorResponse().getHttpStatus());
        } catch(InternalServerErrorException e) {
            throw new InternalServerErrorException();
        }
    }

    @GetMapping(value= {"/interprete/{id}", "/interprete"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value = "id", required = false) String id){
        InterpreteResponse interpreteResponse = interpretiFacade.findById(id);
        if (interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getInterprete(), interpreteResponse.getErrorResponse().getHttpStatus());
        } else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(),interpreteResponse.getErrorResponse().getHttpStatus());
        }
    }

    @GetMapping(value="/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByNomeContains(@RequestParam(name = "name") String name) {
        InterpreteResponse interpreteResponse = interpretiFacade.findByNomeContains(name);
        if(interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getInterpreteList(), interpreteResponse.getErrorResponse().getHttpStatus());
        } else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse().getHttpStatus());
        }
    }

    @GetMapping(value="/cognome",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByCognomeContains(@RequestParam(name="cognome") String cognome){
        InterpreteResponse interpreteResponse = interpretiFacade.findByCognomeContains(cognome);
        if(interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK) {
            return new ResponseEntity<>(interpreteResponse.getInterpreteList(), interpreteResponse.getErrorResponse().getHttpStatus());
        }
        else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse().getHttpStatus());
        }
    }

    @GetMapping(value="/nomeAndCognome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreteByNomeAndCognome(@RequestParam (value="nome") String nome, @RequestParam (value="cognome") String cognome){
        InterpreteResponse interpreteResponse = interpretiFacade.findByNomeAndCognomeContains(nome, cognome);
        if(interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK)
        return new ResponseEntity<>(interpreteResponse.getInterpreteList(),interpreteResponse.getErrorResponse().getHttpStatus());
        else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse(), interpreteResponse.getErrorResponse().getHttpStatus());
        }
    }

//    @GetMapping(value="/ignoringCase", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getAndIgnore(@RequestParam(name="nome") String nome, @RequestParam(name="cognome") String cognome){
//        InterpreteResponse interpreteResponse = interpretiFacade.findByNomeContainsIgnoreCaseOrCognomeIgnoreCaseContainsOrderByCognomeAscNomeAsc(nome, cognome);
//        if(interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK)
//        return new ResponseEntity<>(interpreteResponse.getInterpreteList(), interpreteResponse.getErrorResponse().getHttpStatus());
//        else{
//        return new ResponseEntity<>(interpreteResponse.getErrorResponse().getHttpStatus());
//        }
//    }

    @PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(
            @RequestBody Interprete interprete){
        InterpreteResponse interpreteResponse = interpretiFacade.save(interprete);
        if(interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK){
            return new ResponseEntity<>(interpreteResponse.getInterprete(), interpreteResponse.getErrorResponse().getHttpStatus());
        }
        else {
            return new ResponseEntity<>(interpreteResponse.getErrorResponse().getHttpStatus());
        }
    }

    @PostMapping(value="/update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
            @RequestBody InterpretePojo interprete){
                InterpreteResponse interpreteResponse = interpretiFacade.update(interprete);
                if(interpreteResponse.getErrorResponse().getHttpStatus() == HttpStatus.OK) {
                    return new ResponseEntity<>(interpreteResponse.getInterprete(), interpreteResponse.getErrorResponse().getHttpStatus());
                }
                else{
                    return new ResponseEntity<>(interpreteResponse.getErrorResponse().getHttpStatus());
                }
    }

}
