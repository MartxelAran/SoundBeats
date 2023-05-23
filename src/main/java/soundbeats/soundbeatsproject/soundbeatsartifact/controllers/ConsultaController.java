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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.util.StringUtils;
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
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File directory=new File(UPLOAD_DIR, fileName);
        String[] filenames;
        Boolean repetido=false;
        try {
            if(directory.exists()){
                repetido=true;
                filenames=fileName.split("[.]");
                filenames[0]=filenames[0]+"1";
                fileName=filenames[0]+"."+filenames[1];
            }
            if (!file.getOriginalFilename().equals("")) {
                try {
                    
                    fileUploadUtil.saveFile(UPLOAD_DIR, fileName, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                try {
                    ZipInputStream zipIn = new ZipInputStream(
                            new FileInputStream("src/main/resources/static/audios/" + fileName));
                    ZipEntry entry = zipIn.getNextEntry();
    
                    while (entry != null) {
                        if (!entry.isDirectory()) {
                            String name=entry.getName();
                            if(repetido){
                                String[] names=name.split("[.]");
                                names[0]=names[0]+"1";
                                name=names[0]+"."+names[1];
                            }
                            String filePath = UPLOAD_DIR + "/" + name;
                            Path path = Paths.get(filePath);
                            Files.createDirectories(path.getParent());
                            Files.copy(zipIn, path);
                        }
    
                        zipIn.closeEntry();
                        entry = zipIn.getNextEntry();
                    }
                    System.out.println("Deszipeado");
                    zipIn.close();
    
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Paciente pac=LoggedPaciente.getPaciente();
        Enfermedad enf=new Enfermedad("Gripe", "Descanso, hidratación y medicamentos para aliviar los síntomas");
        model.addAttribute("paciente", pac);
        model.addAttribute("enfermedad", enf);
        Consulta cons=new Consulta(LocalDateTime.now().toString(), "Arrasate", "Nafarroa Hiribidea"
        , null, pac.getNombreMedico(), null,null);
        consUtils.insertConsulta(cons);
        return "diagnostico";
    }
}
