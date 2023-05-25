package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos.Enfermedad;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.ConsultaUtils;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.FileUploadUtil;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.RabbitMQUtil;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ConsultaController {

    @Autowired
    private ConsultaUtils consUtils;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    static final String UPLOAD_DIR = "src/main/resources/static/audios";
    
    @GetMapping("/historial")
    public String getHistoria(Model model){
        List<Consulta> consultas=consUtils.getConsultasByNuss(LoggedPaciente.getPaciente().getNumss());
        model.addAttribute("paciente", LoggedPaciente.getPaciente());
        model.addAttribute("consultas", consultas);
        return "historial";
    }

    @PostMapping("/subirArchivo")
    public String getAudio(@RequestParam("file") MultipartFile file, Model model) {
        String path=fileUploadUtil.getDeszip(file);
        String base64=fileUploadUtil.convertBase64(path);
        Paciente pac=LoggedPaciente.getPaciente();
        Enfermedad enf=new Enfermedad("Murmullo", "Estos ruidos pueden ser sintoma de enfermedades cardiovasculares, se recomienda ponerte en contacto con el medico");
        model.addAttribute("paciente", pac);
        model.addAttribute("enfermedad", enf);
        Consulta cons = new Consulta(LocalDateTime.now().toString(), "Arrasate", "Nafarroa Hiribidea", "null", "nombre",
                0, base64, LoggedPaciente.getPaciente().getNumss().toString(), 1,null);
        consUtils.insertConsulta(cons);
        //RabbitMQUtil ru = new RabbitMQUtil(); 
        //Consulta c = ru.conexion();
        return "diagnostico";
    }
}
