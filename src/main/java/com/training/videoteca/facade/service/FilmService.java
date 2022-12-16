package com.training.videoteca.facade.service;

import com.training.videoteca.dto.*;
import com.training.videoteca.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface FilmService {

    List<FilmTitoloAnnoDTO> findAll();

    TitoloAnnoIdDTO findById(Long id);

    List<FilmTitoloAnnoDTO> findByString(String titolo);

    List<FilmTitoloAnnoDTO> findByDate(String date);

    List<GenereDTO> findByGenere(String genere);

    List<Film> findByAutore(String nome);

    List<Film> getListaCompleta();

    Film getFilmById(Long id);

    Film saveNewFilm(Film film);

    void delete(Film film);

    List<UrlDTO> getUrl(int page);

    InterpretiDTO findInterpretiPerCard(Long id);
}

