package com.training.videoteca.facade;

import com.training.videoteca.POJO.GenerePojo;
import com.training.videoteca.POJO.userResponsePojo;
import com.training.videoteca.entity.Genere;
import com.training.videoteca.facade.service.GenereService;
import com.training.videoteca.errors.pojo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class GenereFacade {
    @Autowired
    GenereService genereService;

    public List<Genere> getAll() {
        return genereService.getAll();
    }

    public userResponsePojo getGenereById(String id){
        userResponsePojo userRP = new userResponsePojo();
        userRP.setGenere(new Genere());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK,"");
        if(id != null && id.isEmpty()) {
            try {
                userRP.setGenere(genereService.getGenereById(Long.valueOf(id)));
                userRP.setHttpStatus(HttpStatus.OK);

                if (userRP.getGenere() == null) {
                    errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                    errorResponse.setMsg("il genere ricercato non è stato trovato");
                }
            } catch (Exception e) {
                errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMsg("si è verificato un errore nella ricerca");
            }
        }else {
                       errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                       errorResponse.setMsg("non è stato fornito un id valido");
            }
        userRP.setErrorResponse(errorResponse);
        return userRP;
    }

    public userResponsePojo findByDescrizioneContaining(String descrizione) {
        userResponsePojo userRP = new userResponsePojo();
        userRP.setGenere(new Genere());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.OK,"");
       if(descrizione != null && descrizione.length()>= 3){
           try{
               userRP.setGenere(genereService.findByDescrizioneContaining(descrizione));
               if(descrizione.isEmpty()){
                   userRP.setHttpStatus(HttpStatus.BAD_REQUEST);
                    errorResponse.setMsg("descrizione non valida");
               }
           } catch(Exception e){
                userRP.setHttpStatus(HttpStatus.NOT_FOUND);
                errorResponse.setMsg("descrizione non trovata");
           }

       }else if(descrizione != null && descrizione.length() <=2){
           errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
           errorResponse.setMsg("non è stato fornito un id valido");
       }
       userRP.setErrorResponse(errorResponse);
    return userRP;
    }


    //
public userResponsePojo postGenereOnDB (Genere genere){
      userResponsePojo gR = new userResponsePojo();
      ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.OK,"");
      Genere g = new Genere();
      g.setDescrizione(genere.getDescrizione());
      Genere doppio = genereService.findByDescrizioneContaining(genere.getDescrizione());

    if(doppio == null) {
          try {
              genereService.postGenereOnDB(g);
              errorResponse.setHttpStatus(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
              errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
              errorResponse.setMsg("--errore 500--");
          }
      } else if (g.getDescrizione()==null && g.getDescrizione().isEmpty()) {
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMsg("Brutta request");
    } else {
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMsg("Non trovato");
    }
//    genere !=null && Strings.isEmpty(genere.getDescrizione())
    gR.setErrorResponse(errorResponse);
    return gR;
}
}
