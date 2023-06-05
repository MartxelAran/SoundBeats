package soundbeats.soundbeatsproject.soundbeatsartifact.sanetizacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ConsultaSanetizacion {
    
    public boolean sanetizarFile(String file){
        String regex = "^.*.wav$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(file));
        return matcher.matches();
    }

    public boolean sanetizarId(Integer id){
        String regex = "[0-9]{1,7}";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(id));
        return matcher.matches();
    }
    
}
