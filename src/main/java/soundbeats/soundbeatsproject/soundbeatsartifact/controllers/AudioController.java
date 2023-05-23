package soundbeats.soundbeatsproject.soundbeatsartifact.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos.Enfermedad;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.FileUploadUtil;

@Controller
public class AudioController {

    static final String UPLOAD_DIR = "src/main/resources/static/audios";

    @PostMapping("/subirArchivor")
    public String getAudio(@RequestParam("file") MultipartFile file, Model model) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File directory=new File(UPLOAD_DIR, fileName);
        String[] filenames;
        Boolean repetido=false;
        if(directory.exists()){
            repetido=true;
            filenames=fileName.split("[.]");
            filenames[0]=filenames[0]+"1";
            fileName=filenames[0]+"."+filenames[1];
        }
        if (!file.getOriginalFilename().equals("")) {
            FileUploadUtil fileUploadUtil = new FileUploadUtil();
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
                e.printStackTrace();
            }
        }
        Paciente pac=LoggedPaciente.getPaciente();
        Enfermedad enf=new Enfermedad("Gripe", "Descanso, hidratación y medicamentos para aliviar los síntomas");
        model.addAttribute("paciente", pac);
        model.addAttribute("enfermedad", enf);
        return "diagnostico";
    }
}
