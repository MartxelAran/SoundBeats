package soundbeats.soundbeatsproject.soundbeatsartifact;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.Paciente;

@SpringBootTest
@Transactional
public class PacienteTest {
    
    @Test
    public void shouldReturnName(){
        Paciente pac=new Paciente();
        pac.setNombre("Martxel");
        assertEquals("Martxel", pac.getNombre());
    }

    @Test
    public void shouldReturnApellido(){
        Paciente pac=new Paciente();
        pac.setApellido("Aranzadi");
        assertEquals("Aranzadi", pac.getApellido());
    }

    @Test
    public void shouldReturnNumss(){
        Paciente pac=new Paciente();
        pac.setNumss("0000000000");
        assertEquals("0000000000", pac.getNumss());
    }

    @Test
    public void shouldReturnFechaNac(){
        Paciente pac=new Paciente();
        pac.setFechanacimiento("22-05-2001");
        assertEquals("22-05-2001", pac.getFechanacimiento());
    }

    @Test
    public void shouldReturnDni(){
        Paciente pac=new Paciente();
        pac.setDni("72852400S");
        assertEquals("72852400S", pac.getDni());
    }

    @Test
    public void shouldReturnCiudad(){
        Paciente pac=new Paciente();
        pac.setCiudad("Arrasate");
        assertEquals("Arrasate", pac.getCiudad());
    }

    @Test
    public void shouldReturnEdad(){
        Paciente pac=new Paciente();
        pac.setEdad(22);
        assertEquals("22", String.valueOf(pac.getEdad()));
    }

    @Test
    public void shouldReturnMedico(){
        Paciente pac=new Paciente();
        pac.setMedico("Laura Martínez");
        assertEquals("Laura Martínez", pac.getMedico());
    }
}
