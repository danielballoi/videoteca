package com.training.videoteca.dto;

import com.training.videoteca.entity.Genere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitoloAnnoIdDTO {
     String titolo;
     String data;
}
