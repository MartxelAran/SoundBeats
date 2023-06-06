package soundbeats.soundbeatsproject.soundbeatsartifact.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import soundbeats.soundbeatsproject.soundbeatsartifact.sanetizacion.ConsultaSanetizacion;

@Component
public class FileUploadUtil {

    @Autowired
    private ConsultaSanetizacion consultaSanetizacion;
    
    static final String UPLOAD_DIR = "src/main/resources/static/audios";

    public Boolean saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
        Path uploadPath = Paths.get(uploadDir);
        InputStream inputStream = multipartFile.getInputStream();
        Path filePath=uploadPath.resolve(fileName);
        long stream=Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        if(stream!=0){
            return true;
        }
        return false;
    }

    public String convertBase64(String path){
        String base64Audio = null;

        try {
            // Read the audio file as bytes
            byte[] audioBytes = Files.readAllBytes(Paths.get(path));

            // Encode the audio bytes as Base64
            base64Audio = Base64.getEncoder().encodeToString(audioBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Audio;
    }

    public String getDeszip(MultipartFile file){
        Path path=null;
        String ret=null;
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        try {
            fileUploadUtil.saveFile(UPLOAD_DIR, fileName, file);

            ZipInputStream zipIn = new ZipInputStream(new FileInputStream("src/main/resources/static/audios/"+fileName));
            ZipEntry entry = zipIn.getNextEntry();
            
            while (entry != null) {
                if (!entry.isDirectory()) {
                    String filename2=entry.getName();
                    if(consultaSanetizacion.sanetizarFile(filename2)){
                        String filePath = UPLOAD_DIR + "/" + filename2;
                        path = Paths.get(filePath);
                        Files.createDirectories(path.getParent());
                        ret="src/main/resources/static/audios/"+filename2;
                        Files.copy(zipIn, path);
                    }
                }
                
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            System.out.println("DesZipeado");
            zipIn.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al subir el archivo");
        }
        return ret;
    } 
}
