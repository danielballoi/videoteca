package com.training.videoteca.errors;

import lombok.Data;

@Data
public class InternalServerErrorException extends Exception{
    private String messag = "Errore di connessione";

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
        this.messag = messag;
    }

    public InternalServerErrorException(String internal) {
    }
}
