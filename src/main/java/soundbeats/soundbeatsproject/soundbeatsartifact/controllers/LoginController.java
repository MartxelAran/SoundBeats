package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.PacienteUtils;

@Controller
public class LoginController {

    @Autowired
    private PacienteUtils pacUtils;
    
    @PostMapping("/login")
    public String getLogin(@RequestParam("numSS") String numSS, @RequestParam("1apellido") String apellido,
        @RequestParam("fechadenacimiento") String fechanacimiento, Model model) {
        Paciente paciente=pacUtils.getPacienteById(numSS, apellido, fechanacimiento);
        model.addAttribute("paciente", paciente);
        LoggedPaciente.setPaciente(paciente);
        return "home";
    }

    @PostMapping("/admin")
    public String adminLogin(@RequestParam("passw") String passw, Model model){
        String retur="login";
        if(passw.equals("1234aA@")){
            retur="redirect:/adminPage";
        }
        return retur;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
