package fer.blog;

//import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

//import org.junit.Rule;
import org.junit.Test;
//import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;

public class MailServiceTest {

	@Test
	public void testEntrySendByMail() {
		MailService t  = new MailService(); 
        String email = "random@mail.com";
        Entry entryMock = Mockito.mock(Entry.class);
        String s[] = {"title", "text", "21/12/2112 21:12:21", "tag1 tag2 tag3","owner"};
        when(entryMock.toStringArray()).thenReturn(s);
        t.entrySendByMail(email, entryMock);
	}
        
//        boolean check = t.query("* from t"); 
//        assertTrue(check); 
//        verify(databaseMock).query("* from t"); 	}

	
//    @Mock
//    Entry entryMock = Mockito.mock(Entry.class); 

//    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 


}