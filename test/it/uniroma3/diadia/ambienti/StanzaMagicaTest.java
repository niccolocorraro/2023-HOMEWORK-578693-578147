package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private StanzaMagica stanza; 
	private Attrezzo attrezzo;
	private Attrezzo utensile;
	private Attrezzo spada;
	
	@BeforeEach  
	void setUp() {
		this.stanza = new StanzaMagica("stanza" ,2);
		this.attrezzo = new Attrezzo("attrezzo",2);
		this.utensile = new Attrezzo("utensile" , 4);
		this.spada = new Attrezzo("spada",10);
		
		this.stanza.addAttrezzo(attrezzo);
		this.stanza.addAttrezzo(utensile);
		this.stanza.addAttrezzo(spada);
	}

	@AfterEach
	void tearDown() {
		stanza = null;
		attrezzo = null;
		utensile = null;
		spada = null;
	}

	@Test
	void test1A() {
		assertEquals("attrezzo", this.stanza.getAttrezzo("attrezzo").getNome());
	}
	
	@Test
	void test1B() {
		assertEquals(2, this.stanza.getAttrezzo("attrezzo").getPeso());
	}
	
	@Test
	void test2A() {
		assertEquals("utensile", this.stanza.getAttrezzo("utensile").getNome());	
	}
	
	@Test
	void test2B() {
		assertEquals(4, this.stanza.getAttrezzo("utensile").getPeso());
	}
	
	@Test
	void test3A() {
		assertEquals("adaps",this.stanza.getAttrezzo("adaps").getNome());
	}

	@Test
	void test3B() {
		assertEquals(20,this.stanza.getAttrezzo("adaps").getPeso());
	}
} 
