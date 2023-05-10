package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.usuario.modelo.Usuario;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.usuario.repositorio.UserRepository;

@Controller
public class LoginController {

    UserRepository ur;
    
    @PostMapping("/login")
    public String getLogin(@RequestParam("numSS") String numSS, @RequestParam("1apellido") String apellido,
        @RequestParam("fechadenacimiento") String fechanacimiento, Model model) {
        System.out.println(numSS+" "+apellido+" "+fechanacimiento.toString());
        Usuario u=ur.findByApellido(apellido);
        System.out.println(u);
        return "home";
    }
}
