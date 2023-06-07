package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

class LabirintoBuilderTest {

	private LabirintoBuilder builder;
	private Labirinto labirinto;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException{ 
		this.builder = Labirinto.newBuilder();
		
	} 
  
	@AfterEach
	void tearDown(){ 
		this.labirinto = null;
	}

	@Test
	void testAddStanzaIniziale() {
		labirinto = this.builder.addStanzaIniziale("stanzaIniziale").getLabirinto();
		assertEquals("stanzaIniziale", this.labirinto.getStanzaCorrente().getNome());
	}
	
	@Test  
	void testAddStanzaVincente(){
		labirinto = this.builder.addStanzaVincente("stanzaVincente").getLabirinto();
		assertEquals("stanzaVincente",this.labirinto.getStanzaVincente().getNome());
		
	}

	@Test 
	void testAddStanza(){
		labirinto = this.builder.addStanzaIniziale("stanza")
				.addStanza("aggiunta")
				.addAdiacenza("stanza", "aggiunta", "nord").getLabirinto();
		assertEquals("aggiunta",this.labirinto.getStanzaCorrente().getStanzaAdiacente("nord").getNome());
	} 
	 
  
	@Test   
	void testAddAdiacenza(){
		labirinto = builder.addStanzaIniziale("stanza")
				.addStanza("stanzaAdicente") 
				.addAdiacenza("stanza", "stanzaAdicente", "nord").getLabirinto();	
		assertEquals("stanzaAdicente",this.labirinto.getStanzaCorrente().getStanzaAdiacente("nord").getNome());
		} 
		   
	@Test  
	void testAddStanzaBloccata(){ 
		labirinto = builder.addStanzaIniziale("iniziale")
				.addStanzaBloccata("bloccata", "chiave", "nord")
                .addAttrezzo("chiave", 1,"bloccata")
                .addAdiacenza("iniziale", "bloccata", "nord").getLabirinto();
        assertEquals("bloccata", labirinto.getStanzaCorrente().getStanzaAdiacente("nord").getNome());
	}
	@Test  
	void testAddStanzaBuia(){ 
		labirinto = this.builder.addStanzaIniziale("stanza")
				.addStanzaBuia("buia","attrezzo")
				.addAttrezzo("attrezzo", 2,"buia").getLabirinto();	
				assertNotEquals("qui c'è buio pesto",this.labirinto.getStanzaCorrente().getDescrizione());
	}
	
	@Test  
	void testAddStanzaMagica(){
		labirinto = this.builder.addStanzaIniziale("stanza")
		.addStanzaMagica("magica")
		.addAdiacenza("stanza", "magica", "nord")
		.addAttrezzo("attrezzo", 1,"magica").getLabirinto();		
		assertTrue(this.labirinto.getStanzaCorrente().getStanzaAdiacente("nord").getAttrezzi().containsKey("attrezzo"));
	} 
	 
	@Test 
	void testAddStanzaMagica2(){
		labirinto = this.builder.addStanzaIniziale("stanza")
		.addStanzaMagica("magica",0)
		.addAdiacenza("stanza", "magica", "nord")
		.addAttrezzo("attrezzo", 2,"magica").getLabirinto();		
		assertTrue(this.labirinto.getStanzaCorrente().getStanzaAdiacente("nord").getAttrezzi().containsKey("ozzertta"));
	} 
	 
	
	
		 
	
}
