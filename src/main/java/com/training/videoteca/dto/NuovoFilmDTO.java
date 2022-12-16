package com.training.videoteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NuovoFilmDTO {
    private String titolo;
    private String data;
    private Long idgenere;
    private List<Long> id_interpreti;
}

