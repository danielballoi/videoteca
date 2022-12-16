package com.training.videoteca.dto;


import com.training.videoteca.entity.Genere;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenereDTO {
    String titolo;
    String data;
    Genere genere;

    public GenereDTO(String titolo, String data, Genere genere) {
        this.titolo = titolo;
        this.data = data;
        this.genere = genere;

    }

}
