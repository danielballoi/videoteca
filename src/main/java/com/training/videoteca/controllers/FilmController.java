package com.training.videoteca.controllers;

import com.training.videoteca.POJO.FilmResponse;
import com.training.videoteca.dto.*;
import com.training.videoteca.entity.Film;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.facade.FilmFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Slf4j
@RestController
@RequestMapping("/film")
public class FilmController {

    private String notValid = "Film non trovato";
    private String internalSE = "Errore nella ricerca";
    private String notFound = "Il film ricercato non esiste";
    @Autowired
    FilmFacade filmFacade;

    @GetMapping(value = "/listaFilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmAnnoTitolo() throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.findAll();
            return new ResponseEntity<>(filmResponse.getFilmTitoloAnnoDTOList(), HttpStatus.OK);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse(), filmResponse.getErrorResponse().getHttpStatus());
        }

    }

    @GetMapping(value = {"/filmId/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmById(@PathVariable String id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.findById(id);
            log.warn(filmResponse.getTitoloAnnoIdDTO().toString());
            TitoloAnnoIdDTO taid = filmResponse.getTitoloAnnoIdDTO();
            return new ResponseEntity<>(taid, HttpStatus.OK);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }

    }

    @GetMapping(value = {"/filmNome"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmByNome(@RequestParam String nome) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.findByString(nome);
            return new ResponseEntity<>(filmResponse.getFilmTitoloAnnoDTOList(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException ne) {
            throw new NotValidException(notValid);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }

    @GetMapping(value = {"/getDate/{date}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDate(@PathVariable String date) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.findByDate(date);
            return new ResponseEntity<>(filmResponse.getFilmTitoloAnnoDTOList(), HttpStatus.OK);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }

    @GetMapping(value = "/getGenere", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGenere(@RequestParam(name = "genere") String genere) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.findByGenere(genere);
            return new ResponseEntity<>(filmResponse.getGenereDTOList(), HttpStatus.OK);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }

    }

    @GetMapping(value = "/getAttoreFromFilm", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAttore(@RequestParam(name = "name") String nome) {
        FilmResponse filmResponse = filmFacade.findByAutore(nome);
        return new ResponseEntity<>(filmResponse.getFilmList(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListaCompleta() throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.getListaCompleta();
            return new ResponseEntity<>(filmResponse.getFilmList(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException ne) {
            throw new NotValidException(notValid);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }

    @GetMapping(value = "/getFilmInfoById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilmInfo(@PathVariable String id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.getFilmById(id);
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (NotFoundException ne) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> publicNewFilmOnDB(@RequestBody NuovoFilmDTO nuovoFilmDTO) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.saveNewFilm(nuovoFilmDTO);
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }

    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateNewOn(@RequestBody NuovoFilmDTO nuovoFilmDTO, @PathVariable(value = "id") Long id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.updateFilm(id, nuovoFilmDTO);
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }

    @PostMapping(value = "/setGenereOn/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setGenereOnFilm(@RequestBody NuovoFilmDTO nuovoFilmDTO, @PathVariable(value = "id", required = false) Long id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.updateGenereOnFilm(id, nuovoFilmDTO);
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException ne) {
            throw new NotValidException(notValid);
        } catch (NotFoundException ne) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }

    }

    @PostMapping(value = "/deleteFilmBy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDeleteFilm(@PathVariable(value = "id") Long id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.deleteFilm(id);
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }

    }

    @PostMapping(value="/delFilm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletingFilm(@RequestBody DeleteFilmDTO deleteFilmDTO){
        FilmResponse filmResponse = new FilmResponse();
        filmResponse = filmFacade.deleteFilmWithReqBody(deleteFilmDTO);
        return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
    }

    @PostMapping(value = "/addInterprete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addInterpreteToFilm(@RequestBody InterpreteDTO interpreteDTO, @PathVariable(value = "id") String id) throws InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.updateAttoreNelFilm(interpreteDTO, id);
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException e) {
            throw new NotValiddException(notValid);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }

    @PostMapping(value = "/deleteInterpreteBy/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteInterpreteFilm(@RequestBody InterpreteDTO interpreteDTO, @PathVariable(value = "id") Long id) throws InternalServerErrorException, NotValidException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        try {
            filmResponse = filmFacade.deleteAttore(interpreteDTO, String.valueOf(id));
            return new ResponseEntity<>(filmResponse.getFilm(), HttpStatus.OK);
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(internalSE);
        } catch (NotValidException e) {
            throw new NotValidException(notValid);
        } catch (NotFoundException e) {
            throw new NotFoundException(notFound);
        } catch (Exception e) {
            return new ResponseEntity<>(filmResponse.getErrorResponse().getHttpStatus());
        }
    }


    @GetMapping(value = "/getfilms/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUrls( @PathVariable(value = "page") int page) {
        log.error("sono entrato");
        List<UrlDTO> longs= filmFacade.getUrl(page);
        return new ResponseEntity<>(longs, HttpStatus.OK);
    }

    @GetMapping(value="/getInterpreti/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInterpreti( @PathVariable(value = "id") Long id) {
         InterpretiDTO dtos= new InterpretiDTO();
        dtos = filmFacade.getInterpretiPerCard(id);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}