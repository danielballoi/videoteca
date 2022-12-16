package com.training.videoteca.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class NotValidException extends Exception{
    private String msg = "informazioni inserite non valide";

    public NotValidException(String msg) {
        this.msg = msg;
    }

    public NotValidException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }
}
