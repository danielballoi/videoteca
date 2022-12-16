package com.training.videoteca.POJO;

import com.training.videoteca.entity.Interprete;
import com.training.videoteca.errors.pojo.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterpreteResponse {
    private Interprete interprete;
    private List<Interprete> interpreteList;
    private ErrorResponse errorResponse;
    private HttpStatus httpStatus;
}
