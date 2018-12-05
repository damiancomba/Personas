package ar.com.superville.personas;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.supervielle.personas.PersonasApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonasApplication.class)
public class PersonasApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testValidandoTipoContenidoDeRespuesta()
	  throws ClientProtocolException, IOException {

		//Invocacion
	   String jsonMimeType = "application/json";
	   HttpUriRequest request = new HttpGet( "http://localhost:8080/persona/idSexo=M;idTipoDocumento=DNI;nroDocumento=8;idPais=2" );
	 
	   HttpResponse response = HttpClientBuilder.create().build().execute( request );
	 
	   // Validacion
	   String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
	   assertEquals( jsonMimeType, mimeType );
	}
	
}
