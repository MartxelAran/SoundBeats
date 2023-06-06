package soundbeats.soundbeatsproject.soundbeatsartifact;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;

@SpringBootTest
@Transactional
public class ConsultaTest {
    @Test
    public void shouldReturnAudio(){
        Consulta cons=new Consulta();
        cons.setAudio("audiobase64");
        assertEquals("audiobase64", cons.getAudio());
    }

    @Test
    public void shouldReturnCiudad(){
        Consulta cons=new Consulta();
        cons.setCiudad("Arrasate");
        assertEquals("Arrasate", cons.getCiudad());
    }

    @Test
    public void shouldReturnDireccion(){
        Consulta cons=new Consulta();
        cons.setDireccion("Erdiko kale");
        assertEquals("Erdiko kale", cons.getDireccion());
    }

    @Test
    public void shouldReturnEnfermedad(){
        Consulta cons=new Consulta();
        cons.setEnfermedad("Murmur");
        assertEquals("Murmur", cons.getEnfermedad());
    }

    @Test
    public void shouldReturnConsultaId(){
        Consulta cons=new Consulta();
        cons.setConsultaid(1);
        assertEquals("1", String.valueOf(cons.getConsultaid()));
    }

    @Test
    public void shouldReturnFecha(){
        Consulta cons=new Consulta();
        cons.setFecha("2023-06-02T11:43:03");
        assertEquals("2023-06-02T11:43:03", cons.getFecha());
    }

    @Test
    public void shouldReturnMedico(){
        Consulta cons=new Consulta();
        cons.setNombreMedico("Laura Martinez");
        assertEquals("Laura Martinez", cons.getNombreMedico());
    }

    @Test
    public void shouldReturnVerficiado(){
        Consulta cons=new Consulta();
        cons.setVerificado(1);
        assertEquals("1", String.valueOf(cons.getVerificado()));
    }

    @Test
    public void shouldReturnNumss(){
        Consulta cons=new Consulta();
        cons.setNuss("0000000000");
        assertEquals("0000000000", cons.getNuss());
    }

    @Test
    public void shouldReturnPaciente(){
        Consulta cons=new Consulta();
        Paciente pac=new Paciente("Martxel", "Aranzadi", "0000000000", null, null, null, null, null);
        cons.setPac(pac);
        assertEquals("Aranzadi", cons.getPac().getApellido());
    }

    @Test
    public void shouldReturnFarmaciaID(){
        Consulta cons=new Consulta();
        cons.setFarmaciaID(1);
        assertEquals("1", String.valueOf(cons.getFarmaciaID()));
    }

    @Test
    public void shouldReturnHospitalID(){
        Consulta cons=new Consulta();
        cons.setHospitalID(1);
        assertEquals("1", String.valueOf(cons.getHospitalID()));
    }
}
