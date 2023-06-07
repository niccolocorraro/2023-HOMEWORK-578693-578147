package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;


/*
 * test che verificano le funzioni getStanzaCorrente , 
 * getStanzaVincente e setStanzaCorrente.
 */ 

public class LabirintoTest {
 
	private Labirinto labirinto;
	private LabirintoBuilder builder;
	
	@BeforeEach 
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.builder = Labirinto.newBuilder();
		
		 
	}
	
	@AfterEach 
	public void tearDown() {
		this.builder = null;
		this.labirinto= null;
		
	}
	
	/*
	 * test per getStanzaCorrente
	 */
	@Test //test con una stanza
	public void testGetStanzaCorrente1() {
		labirinto = this.builder.addStanzaIniziale("stanza").getLabirinto();
		assertEquals("stanza", this.labirinto.getStanzaCorrente().getNome());
	}
	
	
	/*
	 * test per getStanzaVincente
	 */
	@Test  //test stanza vincente di default 
	public void testGetStanzaVincente1() {
		labirinto = this.builder.addStanzaIniziale("stanza")
				.addStanzaVincente("vincente").getLabirinto();
				
		assertEquals(  "vincente"  ,this.labirinto.getStanzaVincente().getNome() );
	} 
	
}

