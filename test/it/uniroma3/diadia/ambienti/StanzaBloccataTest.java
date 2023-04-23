package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata stanza;
	private StanzaBloccata camera;
	private Attrezzo attrezzo;
	

	@BeforeEach
	void setUp() {
		this.attrezzo = new Attrezzo("attrezzo",1);
		this.stanza = new StanzaBloccata("stanza" , attrezzo , "nord");
		this.camera = new StanzaBloccata("camera" , attrezzo , "sud" );
		
		this.stanza.addAttrezzo(attrezzo);
		this.camera.impostaStanzaAdiacente("sud", stanza);
		this.stanza.impostaStanzaAdiacente("nord", camera);
	}

	@AfterEach
	void tearDown() {
		this.attrezzo = null;
		this.stanza = null;
		this.camera = null;
	}
     
	@Test
	void testEntra() {
		assertEquals(camera , this.stanza.getStanzaAdiacente("nord"));
	}

	@Test
	void testNONEntra() {
		assertEquals(camera, this.camera.getStanzaAdiacente("sud"));
	}
}
