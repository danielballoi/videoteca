package com.training.videoteca.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

//oggetto vuoto, utilizzeremo facade per popolarlo

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greetings {
    private String saluto;

}
