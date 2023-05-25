package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("paciente", LoggedPaciente.getPaciente());
        return "home";
    }

    @GetMapping("/exit")
    public String exit(){
        LoggedPaciente.setPaciente(null);
        return "login";
    }
}
