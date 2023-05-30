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

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {
    static final String UPLOAD_DIR = "src/main/resources/static/audios";

    public FileUploadUtil(){}

    public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
        Path uploadPath = (Path) Paths.get(uploadDir);
        InputStream inputStream = multipartFile.getInputStream();
        Path filePath=uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
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
                    path = Paths.get(filePath);
                    Files.createDirectories(path.getParent());
                    Files.copy(zipIn, path);
                    fileName = entry.getName();
                }
                
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
            System.out.println("DesZipeado");
            zipIn.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "src/main/resources/static/audios/" + path.getFileName().toString();
    } 
}
