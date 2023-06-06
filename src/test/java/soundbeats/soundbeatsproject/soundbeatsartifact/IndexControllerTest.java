package soundbeats.soundbeatsproject.soundbeatsartifact;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import soundbeats.soundbeatsproject.soundbeatsartifact.controllers.IndexController;

@SpringBootTest
@Transactional
public class IndexControllerTest {
    
    @Mock
    private IndexController indexControllerMock;

    @Test
    public void testMail() throws Exception {
        MockitoAnnotations.openMocks(this);

        when(indexControllerMock.getLogin()).thenReturn("login");
        String result=indexControllerMock.getLogin();
        assertEquals(result,"login");
    }
}
