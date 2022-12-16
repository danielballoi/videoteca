package com.training.videoteca.controllers;


import com.training.videoteca.POJO.Greetings;
import com.training.videoteca.POJO.userResponsePojo;
import com.training.videoteca.facade.GreetingsSaluti;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accesso")
public class GreetingsController {

    //richiamo il facade e mostrerò l'oggetto nel localhost
    GreetingsSaluti greS = new GreetingsSaluti();


    @GetMapping(value = "/greetings", produces = MediaType.APPLICATION_JSON_VALUE)
    public Greetings saluto() {
        return greS.saluti();

    }


    @GetMapping(value = "/security", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greetings> security(@RequestParam String us, @RequestParam String pas) {
        userResponsePojo urp = greS.getSecurity(us, pas);
        return new ResponseEntity<>(urp.getSaluto(), urp.getHttpStatus());
    }


}