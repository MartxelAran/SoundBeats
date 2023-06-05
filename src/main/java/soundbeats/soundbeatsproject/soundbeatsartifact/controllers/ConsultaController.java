package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos.Enfermedad;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.sanetizacion.ConsultaSanetizacion;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.ConsultaUtils;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.FileUploadUtil;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.PacienteUtils;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.RabbitMQUtil;

import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ConsultaController {

    @Autowired
    private ConsultaUtils consUtils;

    @Autowired
    private PacienteUtils pacUtils;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private ConsultaSanetizacion consultaSanetizacion;

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
        if(path!=null){
            String base64=fileUploadUtil.convertBase64(path);
            Paciente pac=LoggedPaciente.getPaciente();
            model.addAttribute("paciente", pac);
            Consulta cons = new Consulta(null,LocalDateTime.now().toString(), "Arrasate", "Nafarroa Hiribidea", "null", "nombre",
                0, base64, LoggedPaciente.getPaciente().getNumss().toString(), 1,null, null);
            consUtils.insertConsulta(cons);
            RabbitMQUtil ru = new RabbitMQUtil();
            Consulta c = ru.conexion();
            Enfermedad e=consUtils.getDefEnfermedad(c.getEnfermedad());
            model.addAttribute("enfermedad", e);
        }
        return "diagnostico";
    }

    @RequestMapping(path = "/deleteCons/{id}")
    public String deleteCons(@PathVariable("id") Integer id, Model model){
        if(consultaSanetizacion.sanetizarId(id)){
            consUtils.deleteCons(id);
        }
        return "redirect:/adminPage";
    }

    @RequestMapping(path = "/confirmar/{id}")
    public String confirmDiagnostico(@PathVariable("id") Integer id){
        if(consultaSanetizacion.sanetizarId(id)){
            consUtils.validarCons(id);
        }
        return "redirect:/adminPage";
    }

    @RequestMapping(path = "/denegar/{id}")
    public String denegarDiagnostico(@PathVariable("id") Integer id){
        if(consultaSanetizacion.sanetizarId(id)){
            consUtils.denegarCons(id);
        }
        return "redirect:/adminPage";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model){
        List<Consulta> consultas=consUtils.getConsultasPorMedico(1);
        List<Consulta> consultas2=pacUtils.getPacientesPorNuss(consultas);
        model.addAttribute("consultas", consultas2);
        return "admin";
    }
}