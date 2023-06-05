package soundbeats.soundbeatsproject.soundbeatsartifact;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import soundbeats.soundbeatsproject.soundbeatsartifact.domain.diagnosticos.Enfermedad;

@SpringBootTest
@Transactional
public class EnfermedadTest {

    @Test
    public void shouldReturnName(){
        Enfermedad enf=new Enfermedad();
        enf.setNombre("Murmur");
        assertEquals("Murmur", enf.getNombre());
    }

    @Test
    public void shouldReturnDef(){
        Enfermedad enf=new Enfermedad();
        enf.setDefinicion("Es un problema cardiovascular grave");
        assertEquals("Es un problema cardiovascular grave", enf.getDefinicion());
    }
}
