package soundbeats.soundbeatsproject.soundbeatsartifact.sanetizacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class LoginSanetizacion {

    public boolean sanetizarNuss(String nuss){
        String regex = "[0-9]{10}";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(nuss);
        return matcher.matches();
    }

    public boolean sanetizarApellido(String apellido){
        String regex = "[a-zA-Z]{1,30}";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(apellido);
        return matcher.matches();
    }

    public boolean sanetizarContrasena(String contrasena){
        String regex = "[a-zA-Z0-9@$!%*#?&]{8,30}";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(contrasena);
        return matcher.matches();
    }
}
