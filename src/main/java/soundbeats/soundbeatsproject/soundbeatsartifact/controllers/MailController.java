package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

    @GetMapping("mail")
    public String mail(HttpSession sesion){
        if(sesion.getAttribute("paciente")!=null){
            return "mail";
        }
        return "error";
    }
}