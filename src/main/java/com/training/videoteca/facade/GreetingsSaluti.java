package com.training.videoteca.facade;

import com.training.videoteca.POJO.Greetings;
import com.training.videoteca.POJO.userResponsePojo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

//popolazione dell'oggetto
@Component
public class GreetingsSaluti {

     Greetings gr = new Greetings("Hello World");


    public Greetings saluti(){
        return gr;
    }


//    public String isLogged (String us, String pas){
//        if(username.equals(us) && password.equals(pas)){
//            return gr.toString()+ " " + "STATUS 200";
//        } else if(username.equals(us) || password.equals(pas)) {
//            return "STATUS 400";
//        }
//        else {
//            return "STATUS 403";
//        }
//    }

    public userResponsePojo getSecurity(String userName, String Password){
        final String chkUser = "pippo";
        final String chkPsw= "1234";

        //IMPORTANTE, BISOGNA SEMPRE ISTANZIARE
        userResponsePojo urp = new userResponsePojo();
        urp.setSaluto(new Greetings());

        if(Strings.isEmpty(userName) || userName == null) {
          urp.setHttpStatus(HttpStatus.BAD_GATEWAY);
        }
        else if (chkUser.equals(userName)){
            urp.setHttpStatus(HttpStatus.OK);
        }
        else
        {
            urp.setHttpStatus(HttpStatus.NO_CONTENT);
        }
        return urp;
    }


}
