package soundbeats.soundbeatsproject.soundbeatsartifact.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos.Enfermedad;

@Component
public class ConsultaUtils {

    public List<Consulta> getConsultasByNuss(String numSS) {
        List<Consulta> consultas=new ArrayList<>();
        try {
            URL url = new URL("http://soundbeatsnodered.duckdns.org/historial?numss="+numSS);
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
            Consulta[] consultasArray = gson.fromJson(response.toString(), Consulta[].class);
            consultas=Arrays.asList(consultasArray);
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
		return consultas;
	}

    public void insertConsulta(Consulta consulta){
        Client client = ClientBuilder.newClient();
        String url = "http://soundbeatsnodered.duckdns.org/diagnostico";
        Response response = client.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(consulta));

        int status = response.getStatus();
        response.close();

        if (status == Response.Status.CREATED.getStatusCode()) {
            System.out.println("Creado con POST (Objt)");
        } else {
            System.out.println("La llamada no ha sido correcta");
        }
    }

    public Enfermedad getDefEnfermedad(String enf){
        Enfermedad def=null;
        try {
            URL url = new URL("http://soundbeatsnodered.duckdns.org/enfermedad?enfermedad="+enf);
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
            Enfermedad[] consultasArray = gson.fromJson(response.toString(), Enfermedad[].class);
            def=consultasArray[0];
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
        return def;
    }

    public List<Consulta> getConsultasPorMedico(int medicoID){
        List<Consulta> consultas=null;
        try {
            URL url = new URL("http://soundbeatsnodered.duckdns.org/pacientes?medico="+medicoID);
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
            Consulta[] consultasArray = gson.fromJson(response.toString(), Consulta[].class);
            consultas=Arrays.asList(consultasArray);
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
        return consultas;
    }

    public void deleteCons(int consultaid){
        URL url;
        try {
            url = new URL("http://soundbeatsnodered.duckdns.org/deleteCons?consultaid="+consultaid);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void validarCons(int consultaid){
        URL url;
        try {
            url = new URL("http://soundbeatsnodered.duckdns.org/validarCons?consultaid="+consultaid);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void denegarCons(int consultaid){
        URL url;
        try {
            url = new URL("http://soundbeatsnodered.duckdns.org/denegarCons?consultaid="+consultaid);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode();
            System.out.println("Código de respuesta: " + responseCode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}