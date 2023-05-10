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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import soundbeats.soundbeatsproject.soundbeatsartifact.utils.FileUploadUtil;

@Controller
public class AudioController {

    static final String UPLOAD_DIR = "src/main/resources/static/audios";
    
    @PostMapping("/subirArchivo")
    public String getAudio(@RequestParam("file") MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        try {
            fileUploadUtil.saveFile(UPLOAD_DIR, fileName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream("src/main/resources/static/audios/"+fileName));
            ZipEntry entry = zipIn.getNextEntry();
            
            while (entry != null) {
                if (!entry.isDirectory()) {
                    String filePath = UPLOAD_DIR + "/" + entry.getName();
                    Path path = Paths.get(filePath);
                    Files.createDirectories(path.getParent());
                    Files.copy(zipIn, path);
                    fileName = entry.getName();
                }
                
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            System.out.println("Zipeado");
            zipIn.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }
}
