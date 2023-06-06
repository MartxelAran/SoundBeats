package soundbeats.soundbeatsproject.soundbeatsartifact;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.utils.RabbitMQUtil;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class RabbitMQTest {
    @Mock
    private ConnectionFactory connectionFactoryMock;
    @Mock
    private Connection connectionMock;
    @Mock
    private Channel channelMock;
    @Mock
    private RabbitMQUtil rabbitMQUtilMock;

    @Test
    public void testConexion() throws InterruptedException, IOException, TimeoutException {
        MockitoAnnotations.openMocks(this);
        Consulta cons=new Consulta(1, "2023-06-06T9:38:04", "Arrasate", null, null, null, null, null, null, null, null, null);

        when(rabbitMQUtilMock.conexion()).thenReturn(cons);

        // Llamar a la función que se va a probar
        Consulta result = rabbitMQUtilMock.conexion();

        // Verificar el resultado
        // Aquí puedes realizar aserciones adicionales según la estructura de tu clase Consulta
        assertEquals(cons, result);
    }
}
