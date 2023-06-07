package soundbeats.soundbeatsproject.soundbeatsartifact;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import soundbeats.soundbeatsproject.soundbeatsartifact.controllers.MailController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MailControllerTest {

    @Mock
    private MailController mailControllerMock;

    @Test
    public void testMail() throws Exception {
        MockitoAnnotations.openMocks(this);

        when(mailControllerMock.mail(null)).thenReturn("mail");
        String result=mailControllerMock.mail(null);
        assertEquals(result,"mail");
    }
}
