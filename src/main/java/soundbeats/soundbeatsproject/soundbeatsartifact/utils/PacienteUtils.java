package soundbeats.soundbeatsproject.soundbeatsartifact.utils;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;

@Component
public class PacienteUtils {
    public Paciente getPacienteById(String numSS, String apellido, String fechanacimiento) {
        Paciente paciente=null;
        try {
            URL url = new URL("http://soundbeatsnodered.duckdns.org/login?numss="+numSS+"&apellido="+apellido+"&fechanacimiento="+fechanacimiento);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Convertir el JSON a un objeto Java
            System.out.println(response.toString());

            Gson gson=new GsonBuilder().create();
            Paciente[] pacientes = gson.fromJson(response.toString(), Paciente[].class);
            paciente=pacientes[0];
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
		return paciente;
	}

    public List<Consulta> getPacientesPorNuss(List<Consulta> consultas){
        List<Consulta> pac=consultas;
        for(Consulta cn : pac){
            cn.setPac(getPacienteByNuss(cn.getNuss()));
        }
        return consultas;
    }

    public Paciente getPacienteByNuss(String numSS) {
        Paciente paciente=null;
        try {
            URL url = new URL("http://soundbeatsnodered.duckdns.org/paciente?nuss="+numSS);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Convertir el JSON a un objeto Java
            Gson gson=new GsonBuilder().create();
            Paciente[] pacientes = gson.fromJson(response.toString(), Paciente[].class);
            paciente=pacientes[0];
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
		return paciente;
	}
}
