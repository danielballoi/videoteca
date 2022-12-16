package com.training.videoteca.dto;

import com.training.videoteca.entity.Film;
import com.training.videoteca.entity.Interprete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpreteDTO {
    List<Interprete> interpreteListaPerDTO;
    String titolo;
    String anno;
    Long idInterprete;

}
