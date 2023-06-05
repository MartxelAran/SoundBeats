package soundbeats.soundbeatsproject.soundbeatsartifact.tests.modelTest;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.medico.Medico;

@SpringBootTest
@Transactional
public class MedicoTest {
    
    @Test
    public void shouldReturnName(){
        Medico med=new Medico();
        med.setNombrecompleto("Laura Martinez");
        assertEquals("Laura Martinez", med.getNombrecompleto());
    }

    @Test
    public void shouldReturnPassw(){
        Medico med=new Medico();
        med.setPassword("asdasdasdsa213safsag.&%$%asdas");
        assertEquals("asdasdasdsa213safsag.&%$%asdas", med.getPassword());
    }

    @Test
    public void shouldReturnDni(){
        Medico med=new Medico();
        med.setDni("81239412O");
        assertEquals("81239412O", med.getDni());
    }
}
