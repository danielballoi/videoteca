package com.training.videoteca.dto;

import com.training.videoteca.entity.Interprete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpretiDTO {
    private Set<Interprete> interpreteSet;
}
