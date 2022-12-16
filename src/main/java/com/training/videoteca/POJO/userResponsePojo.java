package com.training.videoteca.POJO;

import com.training.videoteca.entity.Genere;
import com.training.videoteca.errors.pojo.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userResponsePojo {

    private Genere genere;
    private Greetings saluto;
    private List<Genere> genereList;
    private HttpStatus httpStatus;
    private ErrorResponse errorResponse;

}
