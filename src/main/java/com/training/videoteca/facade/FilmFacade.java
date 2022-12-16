package com.training.videoteca.facade;

import com.sun.istack.NotNull;
import com.training.videoteca.POJO.FilmResponse;
import com.training.videoteca.dto.*;
import com.training.videoteca.entity.Film;
import com.training.videoteca.entity.Interprete;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.facade.service.FilmService;
import com.training.videoteca.facade.service.GenereService;
import com.training.videoteca.facade.service.InterpretiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.StreamSupport.stream;

@Component
@Slf4j
public class FilmFacade {

    @Autowired
    private FilmService filmService;
    @Autowired
    private InterpretiService interServ;
    @Autowired
    private GenereService genServ;

    private String notValid = "film non trovato";
    private String internalSE = "errore nella ricerca";
    private String notFound = "il film ricercato non esiste";
    public FilmResponse findAll() throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(!filmService.findAll().isEmpty()) {
            try {
                filmResponse.setFilmTitoloAnnoDTOList(filmService.findAll());
                if(filmService.findAll().isEmpty()){
                    throw new NotValidException(notValid);
                }
            }  catch(NotValidException ne){
                throw new NotValidException(notValid);
            } catch (Exception isee) {
                throw new InternalServerErrorException(internalSE);
            }
        }else {
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse findById(String id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(id != null && !id.isEmpty()){
            try {
                TitoloAnnoIdDTO prova = new TitoloAnnoIdDTO();
                prova = filmService.findById(Long.valueOf(id));
                //TitoloAnnoIdDTO gettingFilms = filmService.findById(Long.valueOf(id));
                filmResponse.setTitoloAnnoIdDTO(prova);
                log.error(filmResponse.getTitoloAnnoIdDTO().toString());
                if (filmService.findById(Long.valueOf(id)) == null) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex) {
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else{
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse findByString(String nome) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if (nome != null && !nome.isEmpty()) {
            try {
                filmResponse.setFilmTitoloAnnoDTOList(filmService.findByString(nome));
                if (filmService.findByString(nome).isEmpty()) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex) {
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse findByDate(String date) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(date != null && !date.isEmpty()){
            try{
                filmResponse.setFilmTitoloAnnoDTOList(filmService.findByDate(date));
                if(filmService.findByDate(date).isEmpty()) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex) {
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }

        return filmResponse;
    }

    public FilmResponse findByGenere(String genere) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(genere != null && !genere.isEmpty()) {
            try{
                filmResponse.setGenereDTOList(filmService.findByGenere(genere));
                if(filmService.findByGenere(genere).isEmpty()) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex){
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else{
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse findByAutore(String nome){
        FilmResponse filmResponse = new FilmResponse();
        if(nome != null && !nome.isEmpty())
        filmResponse.setFilmList(filmService.findByAutore(nome));
        return filmResponse;
    }

    public  FilmResponse getListaCompleta() throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if((filmService.getListaCompleta()) != null) {
            try {
                filmResponse.setFilmList(filmService.getListaCompleta());
                if (filmResponse.getFilmList() == null || filmResponse.getFilmList().isEmpty()) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex) {
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else{
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse getFilmById(String id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(id != null && !id.isEmpty()) {
            try {
                filmResponse.setFilm(filmService.getFilmById(Long.valueOf(id)));
                if (filmService.getFilmById(Long.valueOf(id)) == null) {
                    throw new NotValidException(notValid);
                }
            }catch(NotValidException ex){
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse saveNewFilm(NuovoFilmDTO nuovoFilmDTO) throws NotFoundException, NotValidException, InternalServerErrorException {
        FilmResponse filmResponse = new FilmResponse();
        if(nuovoFilmDTO != null) {
            try {
                Set<Interprete> numInterpretiPerF = new HashSet<>();
                for (Long l : nuovoFilmDTO.getId_interpreti()) {
                    numInterpretiPerF.add(interServ.findById(l));
                }
                Film addingFilm = new Film(nuovoFilmDTO.getTitolo(), nuovoFilmDTO.getData(), genServ.getGenereById(nuovoFilmDTO.getIdgenere()), numInterpretiPerF);

                filmService.saveNewFilm(addingFilm);
                filmResponse.setFilm(addingFilm);
                if (numInterpretiPerF.isEmpty()) {
                    throw new NotValidException(notValid);
                }
            } catch(NotValidException n) {
                throw new NotValidException(notValid);
            } catch(Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }
     return filmResponse;
    }

    public FilmResponse updateFilm(Long id,NuovoFilmDTO nuovoFilmDTO) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(nuovoFilmDTO != null) {
            try {
                Film f = filmService.getFilmById(id);
                f.setTitolo(nuovoFilmDTO.getTitolo());
                f.setData(nuovoFilmDTO.getData());
                filmResponse.setFilm(filmService.saveNewFilm(f));
                if(filmService.getFilmById(id) == null){
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ne) {
                throw new NotValidException(notValid);
            } catch (Exception e){
                throw new InternalServerErrorException(internalSE);
            }
        }
        else{
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse updateGenereOnFilm(Long id,NuovoFilmDTO nuovoFilmDTO) throws InternalServerErrorException, NotFoundException, NotValidException {
        FilmResponse filmResponse = new FilmResponse();
        if(nuovoFilmDTO != null) {
            try {
                Film fI = filmService.getFilmById(id);
                fI.setGenere(genServ.getGenereById(nuovoFilmDTO.getIdgenere()));
                filmResponse.setFilm(filmService.saveNewFilm(fI));
                if(filmService.getFilmById(id) == null) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ne){
                throw new NotValidException(notValid);
            } catch (Exception ne) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse deleteFilm(Long id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(id != null) {
            try {
                Film f = filmService.getFilmById(id);
                Film fDaCancellare = new Film(f.getTitolo(), f.getData());
                filmService.delete(f);
                filmResponse.setFilm(fDaCancellare);
                if(filmService.getFilmById(id) != null){
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ne) {
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        }else {
                throw new NotFoundException(notFound);
            }
        return filmResponse;
    }

    public FilmResponse deleteFilmWithReqBody (DeleteFilmDTO deleteFilmDTO) {
        FilmResponse filmResponse = new FilmResponse();
        Film f = filmService.getFilmById(deleteFilmDTO.getId());
        Film fDaCancellare = new Film(f.getTitolo(), f.getData());
        filmService.delete(f);
        filmResponse.setFilm(fDaCancellare);
        return filmResponse;
    }

    public FilmResponse updateAttoreNelFilm(InterpreteDTO interpreteDTO, String id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(interpreteDTO != null && !id.isEmpty()) {
            try {
                Film fRicerca = filmService.getFilmById(Long.valueOf(id));
                fRicerca.getInterpreteSet().add(interServ.findById(interpreteDTO.getIdInterprete()));
                filmService.saveNewFilm(fRicerca);
                filmResponse.setFilm(fRicerca);
                if(filmService.getFilmById(Long.valueOf(id)) == null && interServ.findById(interpreteDTO.getIdInterprete()) == null  ){
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex) {
                throw new NotValidException(notValid);
             } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }

    public FilmResponse deleteAttore(InterpreteDTO interpreteDTO, String id) throws NotValidException, InternalServerErrorException, NotFoundException {
        FilmResponse filmResponse = new FilmResponse();
        if(interpreteDTO != null && !id.isEmpty()) {
            try {
                Film filmDaEliminare = filmService.getFilmById(Long.valueOf(id));
                filmDaEliminare.getInterpreteSet().remove(interServ.findById(interpreteDTO.getIdInterprete()));
                filmService.saveNewFilm(filmDaEliminare);
                filmResponse.setFilm(filmDaEliminare);
                if(interServ.findById(interpreteDTO.getIdInterprete())== null) {
                    throw new NotValidException(notValid);
                }
            } catch (NotValidException ex) {
                throw new NotValidException(notValid);
            } catch (Exception e) {
                throw new InternalServerErrorException(internalSE);
            }
        } else {
            throw new NotFoundException(notFound);
        }
        return filmResponse;
    }
    public List<UrlDTO> getUrl(int page) {
        FilmResponse filmResponse = new FilmResponse();
        List<UrlDTO> urlDTOList=filmService.getUrl(page);
        return urlDTOList;
    }

    public InterpretiDTO getInterpretiPerCard(Long id) {
        InterpretiDTO gettingActors = filmService.findInterpretiPerCard(id);
        return gettingActors;
    }
}
