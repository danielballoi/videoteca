package com.training.videoteca.dto;

import com.training.videoteca.entity.Genere;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmTitoloAnnoDTO {
    String titolo;
    String data;



    public FilmTitoloAnnoDTO(String titolo, String data) {
        this.titolo = titolo;
        this.data= data;
    }
}
