package com.training.videoteca.repository;

import com.training.videoteca.dto.FilmTitoloAnnoDTO;
import com.training.videoteca.dto.GenereDTO;
import com.training.videoteca.dto.InterpretiDTO;
import com.training.videoteca.dto.TitoloAnnoIdDTO;
import com.training.videoteca.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

@Query("SELECT new com.training.videoteca.dto.FilmTitoloAnnoDTO (f.titolo, f.data) FROM Film f")
        List<FilmTitoloAnnoDTO> findQueryTitoloAnno();

@Query("SELECT new com.training.videoteca.dto.TitoloAnnoIdDTO (f.titolo, f.data) FROM Film f WHERE f.id=:id")
        TitoloAnnoIdDTO findTitoloAnnoByID(@Param("id") Long id);


@Query("SELECT new com.training.videoteca.dto.FilmTitoloAnnoDTO (f.titolo, f.data) FROM Film f WHERE f.titolo LIKE %:nome%")
        List<FilmTitoloAnnoDTO>  findByString(@Param("nome") String nome);

@Query("SELECT new com.training.videoteca.dto.FilmTitoloAnnoDTO (f.titolo, f.data) FROM Film f WHERE f.data=:date")
        List<FilmTitoloAnnoDTO> findByDate(@Param("date") String date);

@Query("SELECT new com.training.videoteca.dto.GenereDTO (f.titolo, f.data, g) FROM Film f inner JOIN Genere g ON f.idgenere = g.id WHERE g.descrizione LIKE %:genere%")
        List<GenereDTO> findByGenere(@Param("genere") String genere);

@Query("SELECT f  "+
        "FROM Film f " +
        "JOIN f.interpreteSet i WHERE i.nome LIKE  %:nome% OR i.cognome LIKE  %:nome%  ")
        List<Film> findByAutore(@Param("nome") String nome);

//@Query("SELECT f  FROM Film as f JOIN f.interpreteSet as i group by f.id")
        //List<Film> getListaCompleta();

@Query("SELECT f FROM Film as f join f.interpreteSet WHERE f.id=:id group by f.id")
        Film getFilmById(@Param("id") Long id);

        Page<Film> findAll(Pageable pageable);

        @Query("SELECT f FROM Film as f join f.interpreteSet WHERE f.id=:id group by f.id")
        Film findInterpretiPerCard(@Param("id") Long id);
}

