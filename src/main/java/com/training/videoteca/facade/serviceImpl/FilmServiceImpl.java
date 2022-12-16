package com.training.videoteca.facade.serviceImpl;

import com.training.videoteca.dto.*;
import com.training.videoteca.entity.Film;
import com.training.videoteca.facade.service.FilmService;
import com.training.videoteca.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override

    public List<FilmTitoloAnnoDTO> findAll() {
        return filmRepository.findQueryTitoloAnno();
    }

    @Override

    public TitoloAnnoIdDTO findById(Long id) {
        return filmRepository.findTitoloAnnoByID(id);
    }

    @Override

    public List<FilmTitoloAnnoDTO> findByString(String nome) {
        return filmRepository.findByString(nome);
    }

    @Override

    public List<FilmTitoloAnnoDTO> findByDate(String date) {
        return filmRepository.findByDate(date);
    }

    @Override

    public List<GenereDTO> findByGenere(String genere) {
        return filmRepository.findByGenere(genere);
    }

    @Override

    public List<Film> findByAutore(String nome) {
        return filmRepository.findByAutore(nome);
    }

    @Override

    public List<Film> getListaCompleta() {
        return filmRepository.findAll();
    }


    @Override

    public Film getFilmById(Long id) {
        return filmRepository.getFilmById(id);
    }

    @Override
    @Transactional
    public Film saveNewFilm(Film film) {

        Film filmDaSalvare = film;
        log.info(filmDaSalvare.toString());
        filmDaSalvare = filmRepository.save(film);
        log.info(filmDaSalvare.toString());

        return filmDaSalvare;
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmRepository.delete(film);
    }

    @Override
    public  List<UrlDTO> getUrl(int page) {
        Pageable firstPageWithFourElements = PageRequest.of(page, 4);
        Page<Film> allFilms=filmRepository.findAll(firstPageWithFourElements);
        List<Film> films = allFilms.getContent();
        //films.addAll(allFilms.getContent());

         List<UrlDTO> urlList= films.stream().map(film -> modelMapper.map(film, UrlDTO.class)).collect(Collectors.toList());

//        List<Long> ids= films.stream().map(Film :: getId).collect(Collectors.toList());

       return urlList ;
    }

    @Override
    public InterpretiDTO findInterpretiPerCard(Long id) {
       Film film = filmRepository.findInterpretiPerCard(id);
       InterpretiDTO intDto = this.modelMapper.map(film, InterpretiDTO.class);
        return intDto;
    }


//    public UrlDTO filmToDto(Film film){
//        UrlDTO urlDTO = this.modelMapper.map(film, UrlDTO.class);
//        return urlDTO;
//    }


}
