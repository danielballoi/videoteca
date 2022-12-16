package com.training.videoteca.facade;

import com.training.videoteca.POJO.InterpretePojo;
import com.training.videoteca.POJO.InterpreteResponse;
import com.training.videoteca.entity.Interprete;
//import com.training.videoteca.facade.service.InterpretiService;
import com.training.videoteca.errors.InternalServerErrorException;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.facade.service.InterpretiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InterpretiFacade {

    @Autowired
    InterpretiService interServ;

    public InterpreteResponse getAll() throws InternalServerErrorException {
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK, "");
        if (interServ.getAll() != null) {
            try {
                interpreteResponse.setInterpreteList(interServ.getAll());
            } catch (Exception e) {
                throw new InternalServerErrorException();
            }
            interpreteResponse.setErrorResponse(errorResponse);
        }
        return interpreteResponse;
    }

    public InterpreteResponse findById(String id) {
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        interpreteResponse.setInterprete(new Interprete());
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK, "");
        if (id != null && !id.isEmpty()) {
            try {
                interpreteResponse.setInterprete(interServ.findById(Long.valueOf(id)));
                interpreteResponse.setHttpStatus(HttpStatus.OK);

                if (interpreteResponse.getInterprete() == null) {
                    errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                    errorResponse.setMsg("l'interprete non è stato trovato");
                }
            } catch (Exception e) {
                errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMsg("errore nella ricerca");
            }
        } else {
            errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMsg("non è stato fornito un id valido");

        }
        interpreteResponse.setErrorResponse(errorResponse);
        return interpreteResponse;
    }

    public InterpreteResponse findByNomeContains(String nome) {
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK, "");
        List<Interprete> lista = new ArrayList<>();
        interpreteResponse.setInterpreteList(lista);
        if (nome != null && nome.length() >= 3) {
            try {
                if (!interServ.findByNomeContains(nome).isEmpty()) {
                    interpreteResponse.setInterpreteList(interServ.findByNomeContains(nome));
                    errorResponse.setHttpStatus(HttpStatus.OK);
                } else {
                    errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                    errorResponse.setMsg("interprete non trovato");
                }
            } catch (Exception e) {
                errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMsg("si è verificato un errore nella ricerca");
            }
        } else {
            errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMsg("non è stato fornita una descrizione valida");
        }
        interpreteResponse.setErrorResponse(errorResponse);
        return interpreteResponse;
    }

    public InterpreteResponse findByCognomeContains(String cognome) {
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK, "");
        List<Interprete> lista = new ArrayList<>();
        interpreteResponse.setInterpreteList(lista);
        if (cognome != null && cognome.length() >= 3)
            try {
                if (!interServ.findByCognomeContains(cognome).isEmpty()) {
                    interpreteResponse.setInterpreteList(interServ.findByCognomeContains(cognome));
                    errorResponse.setHttpStatus(HttpStatus.OK);
                } else {
                    errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                    errorResponse.setMsg("interprete non trovato");
                }
            } catch (Exception e) {
                errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setMsg("si è verificato un errore nella ricerca");
            }
        else {
            errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            errorResponse.setMsg("non è stato fornito un cognome valido");
        }
        interpreteResponse.setErrorResponse(errorResponse);
        return interpreteResponse;
    }


    public InterpreteResponse findByNomeAndCognomeContains(String nome, String cognome) {
        log.error("*" + nome + "*" + cognome);
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK, "");
        List<Interprete> lista = new ArrayList<>();
        if (nome != null && cognome != null) {
            try {
                lista = interServ.findByNomeAndCognomeContaining(nome, cognome);
                log.error(""+ lista);
                interpreteResponse.setInterpreteList(lista);
                if (cognome.isEmpty()) {
                    errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    errorResponse.setMsg("si è verificato un errore nella ricerca");
                }
            } catch (Exception e) {
                errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                errorResponse.setMsg("l'interprete non è stato trovato");
            }
        }
            else{
                errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                errorResponse.setMsg("si è verificato un errore nella ricerca");
            }
        interpreteResponse.setErrorResponse(errorResponse);
        return interpreteResponse;
    }


//    public InterpreteResponse findByNomeContainsIgnoreCaseOrCognomeIgnoreCaseContainsOrderByCognomeAscNomeAsc(String nome, String cognome){
//       InterpreteResponse interpreteResponse = new InterpreteResponse();
//       ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.OK,"");
//       List<Interprete> lista = new ArrayList<>();
//       if(nome != null) {
//        try {
//             if(!interServ.findByFiltroNomeECognome(nome,cognome).isEmpty()) {
//                 interpreteResponse.setInterpreteList(interServ.findByFiltroNomeECognome(nome, cognome));
//                 errorResponse.setHttpStatus(HttpStatus.OK);
//             }
//         else {
//             errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
//             errorResponse.setMsg("ricerca non trovata");
//         }
//        } catch(Exception e) {
//            errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//            errorResponse.setMsg("si è verificato un errore nella ricerca");
//        }
//             }
//        else {
//            errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
//            errorResponse.setMsg("non è stata fornita una ricerca valida");
//           }
//
//        interpreteResponse.setInterpreteList(lista);
//        return interpreteResponse;
//    }

    public InterpreteResponse save(Interprete interprete){
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.OK, "");
        Interprete interprete1 = new Interprete();
        interprete1.setNome(interprete.getNome());
        interprete1.setCognome(interprete.getCognome());
        interpreteResponse.setInterprete(interprete1);
        if(interprete.getNome() != null && interprete.getCognome() != null){
            if(!interprete.getNome().isEmpty() && !interprete.getCognome().isEmpty()) {
                try {
                    interpreteResponse.setInterprete(interServ.save(interprete));
                    errorResponse.setHttpStatus(HttpStatus.OK);
                } catch (Exception e) {
                    errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            else{
                    errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
                }
            }
            else{
                errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);

        }
        interpreteResponse.setErrorResponse(errorResponse);
       return interpreteResponse;
    }

    public InterpreteResponse update(InterpretePojo interpretePojo) {
        InterpreteResponse interpreteResponse = new InterpreteResponse();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),HttpStatus.OK, "");

        Long gettingId = interpretePojo.getId();
        Interprete interpreteEsistente = interServ.findById(gettingId);
        Interprete daAggiungere = new Interprete(interpretePojo.getId(), interpretePojo.getNome(), interpretePojo.getCognome());
        if(interpreteEsistente != null) {
        log.info(daAggiungere.toString());
            interpreteResponse.setInterprete(interServ.save(daAggiungere));
            log.info("Dopo");

            errorResponse.setHttpStatus(HttpStatus.OK);
            interpreteResponse.setErrorResponse(errorResponse);
            //gestione non trovato NOT FOUND

        }
        else{
            errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            interpreteResponse.setErrorResponse(errorResponse);
        }
        return interpreteResponse;
    }
}
