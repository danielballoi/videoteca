package com.training.videoteca.errors;

import lombok.Data;

@Data
public class NotFoundException extends Exception{

 private String msg = "Elemento ricercato non trovato";

    public NotFoundException(){
        super();
    }

    public NotFoundException(String msg) {
        this.msg = msg;
    }

    public NotFoundException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }
}
