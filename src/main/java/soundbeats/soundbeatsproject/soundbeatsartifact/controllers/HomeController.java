package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String getHome(Model model){
        return "home";
    }

    @GetMapping("/exit")
    public String exit(HttpSession sesion){
        sesion.setAttribute("paciente", null);
        sesion.setAttribute("medico", null);
        return "login";
    }
}
