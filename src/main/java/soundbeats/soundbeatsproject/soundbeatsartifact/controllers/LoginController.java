package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.medico.Medico;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.sanetizacion.LoginSanetizacion;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.PacienteUtils;

@Controller
public class LoginController {

    @Autowired
    private PacienteUtils pacUtils;

    @Autowired LoginSanetizacion loginSanetizacion;
    
    @PostMapping("/login")
    public String getLogin(@RequestParam("numSS") String numSS, @RequestParam("1apellido") String apellido,
        @RequestParam("fechadenacimiento") String fechanacimiento, Model model) {
        String ret="login";
        if(loginSanetizacion.sanetizarNuss(numSS)&&loginSanetizacion.sanetizarApellido(apellido)){
            Paciente paciente=pacUtils.getPacienteById(numSS, apellido, fechanacimiento);
            model.addAttribute("paciente", paciente);
            LoggedPaciente.setPaciente(paciente);
            ret="home";
        }
        return ret;
    }

    @PostMapping("/admin")
    public String adminLogin(@RequestParam("passw") String passw, Model model){
        BCryptPasswordEncoder cript=new BCryptPasswordEncoder();
        String retur="login";
        Medico med=null;
        if(loginSanetizacion.sanetizarContrasena(passw)){
            med=pacUtils.getMedicoById(1);
        }
        if(med!=null&&cript.matches(passw, med.getPassword())){
            retur="redirect:/adminPage";
        }
        return retur;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
