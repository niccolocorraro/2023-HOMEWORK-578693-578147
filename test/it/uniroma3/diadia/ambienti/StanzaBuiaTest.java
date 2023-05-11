package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia stanza;
	private Attrezzo attrezzo;
	private StanzaBuia camera;
	
 
	@BeforeEach
	void setUp()  {
		this.attrezzo = new Attrezzo("attrezzo",1);
		this.stanza = new StanzaBuia("stanza",attrezzo.getNome());
		this.camera = new StanzaBuia("camera",attrezzo.getNome());
		this.stanza.addAttrezzo(attrezzo);
	}

	@AfterEach 
	void tearDown(){
		this.stanza = null;
		this.attrezzo = null;
		this.camera = null;
	}

	@Test
	void test1() {
		assertEquals( "qui c'è buio pesto", camera.getDescrizione()  );
	}
	
	@Test 
	void test2() { 
		assertNotEquals("qui c'è buio pesto", stanza.getDescrizione()  );
	}

}
