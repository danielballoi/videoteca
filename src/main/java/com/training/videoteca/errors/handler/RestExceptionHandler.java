package com.training.videoteca.errors.handler;

import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.NotFoundException;
import com.training.videoteca.errors.NotValidException;
import com.training.videoteca.errors.pojo.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends Exception {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotFound(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setHttpStatus(HttpStatus.NOT_FOUND);
        errore.setMsg(ex.getMessage());
        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotValidException.class)
    public final ResponseEntity<ErrorResponse> exceptionNotValid(Exception e){
        ErrorResponse errore = new ErrorResponse();
        errore.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public final ResponseEntity<ErrorResponse> exceptionInternalServerError(Exception ex){
        ErrorResponse errore = new ErrorResponse();
        errore.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
