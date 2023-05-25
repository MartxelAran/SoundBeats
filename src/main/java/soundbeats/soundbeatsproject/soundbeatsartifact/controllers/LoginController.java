package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.PacienteUtils;

@Controller
public class LoginController {

    @Autowired
    private PacienteUtils jpaUtils;
    
    @PostMapping("/login")
    public String getLogin(@RequestParam("numSS") String numSS, @RequestParam("1apellido") String apellido,
        @RequestParam("fechadenacimiento") String fechanacimiento, Model model) {
        Paciente paciente=jpaUtils.getPacienteById(numSS, apellido, fechanacimiento);
        model.addAttribute("paciente", paciente);
        LoggedPaciente.setPaciente(paciente);
        return "home";
    }
}
