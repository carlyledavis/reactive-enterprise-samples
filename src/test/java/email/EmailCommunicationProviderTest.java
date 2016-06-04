package email;

import org.junit.Before;
import org.junit.Test;
import payments.PurchaseConfirmation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EmailCommunicationProviderTest {

    private PurchaseConfirmation purchaseConfirmation;

    @Before
    public void setUp() throws Exception {
        purchaseConfirmation = mock(PurchaseConfirmation.class);
    }

    @Test
    public void shouldPostEmailtoEmailServer(){
        EmailServer emailServer = mock(EmailServer.class);
        EmailCommunicationProvider provider = new EmailCommunicationProvider(emailServer);
        provider.sendEmail(purchaseConfirmation);

        verify( emailServer ).send( any());
    }




}