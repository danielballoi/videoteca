package com.training.videoteca.POJO;

import com.training.videoteca.dto.*;
import com.training.videoteca.entity.Film;
import com.training.videoteca.errors.pojo.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmResponse {
    private Film film;
    private List<Film> filmList;
    private List<FilmTitoloAnnoDTO> filmTitoloAnnoDTOList;
    private List<GenereDTO> genereDTOList;
    private GenereDTO genDto;
    private TitoloAnnoIdDTO titoloAnnoIdDTO;
    private HttpStatus httpStatus;
    private ErrorResponse errorResponse;
    private List<UrlDTO> urlDTOList;
    private List<Long> longList;
    private Page<Film> filmPage;
    private DeleteFilmDTO deleteFilmDTO;
}
