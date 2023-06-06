package soundbeats.soundbeatsproject.soundbeatsartifact;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Base64;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import soundbeats.soundbeatsproject.soundbeatsartifact.utils.FileUploadUtil;

public class FileUploadTest {

    @Mock
    private FileUploadUtil fileUploadUtilMock;

    @Test
    public void testSaveFile() throws IOException {
        MockitoAnnotations.openMocks(this);

        // Directorio de prueba para guardar el archivo
        String uploadDir = "src/test/resources/uploaded";

        // Nombre del archivo de prueba
        String fileName = "testfile.txt";

        // Crear un archivo de prueba
        byte[] content = "This is a test file.".getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile("file", fileName, "text/plain", content);

        // Mockear la clase FileUploadUtil y el método saveFile
        when(fileUploadUtilMock.saveFile(uploadDir, fileName, multipartFile)).thenReturn(true);

        // Guardar el archivo utilizando el método saveFile
        boolean result = fileUploadUtilMock.saveFile(uploadDir, fileName, multipartFile);

        // Verificar el resultado
        assertTrue(result, "El archivo no se guardó correctamente.");
        verify(fileUploadUtilMock).saveFile(uploadDir, fileName, multipartFile);
    }

    @Test
    public void testConvertBase64() {
        MockitoAnnotations.openMocks(this);
        String text="Hola texto de prueba";
        // Leer el archivo como bytes
        byte[] audioBytes;
        audioBytes = text.getBytes();

        // Codificar los bytes como Base64
        String expectedBase64 = Base64.getEncoder().encodeToString(audioBytes);

        // Llamar a la función convertBase64
        when(fileUploadUtilMock.convertBase64(text)).thenReturn(expectedBase64);
        String result = fileUploadUtilMock.convertBase64(text);

        // Verificar el resultado
        assertEquals(expectedBase64, result);
    }

    @Test
    public void testGetDeszip() throws IOException {
        MockitoAnnotations.openMocks(this);

        // Archivo comprimido de prueba
        byte[] zipFileData = {0x50, 0x4B, 0x03, 0x04, 0x0A, 0x00, 0x00, 0x00}; // Bytes del archivo comprimido
        MockMultipartFile multipartFile = new MockMultipartFile("file", "testfile.zip", "application/zip", zipFileData);
        
        // Mockear el método saveFile de FileUploadUtil
        when(fileUploadUtilMock.getDeszip(multipartFile)).thenReturn("src/test/resources/testfile.wav");

        // Llamar a la función getDeszip
        String result = fileUploadUtilMock.getDeszip(multipartFile);

        assertTrue(result.startsWith("src/test/resources/testfile.wav"));
    }
}
