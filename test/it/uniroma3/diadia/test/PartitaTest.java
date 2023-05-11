package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;


// Test che si riferisce alla classe partita e testa le funzioni :
 //setFinita, getStanzaCorrente e getCf



public class PartitaTest {
	
	private Partita partita;
	private Partita gioco;
	private Labirinto lab;
	private Labirinto lab2;
	
	@BeforeEach  
	public void setUp() {
		this.lab2 = new Labirinto();
		this.lab = new Labirinto();
		this.partita = new Partita(lab);
		this.gioco = new Partita(lab2); 
		this.partita.setFinita();
		this.gioco.getLabirinto().setStanzaCorrente(this.gioco.getLabirinto().getStanzaVincente());

	}
 
	@AfterEach
	public void tearDown() {
		this.lab = null;
		this.partita=null;
		this.gioco= null;
	}

	
	//test da fare
	
	/*
	 * test per isFinita
	 */
	@Test // test con una partita finita
	public void testsetFinita1() {
		assertEquals(true, this.partita.isFinita());
	}
	
	@Test // test con una partita non finita 
	public void testsetFinita2() {
		assertEquals(true, this.gioco.isFinita());
	}
	
	
	
	/*
	 * test per vinta 
	 */
	
	
	 
	@Test 
	void testVinta2() {
		assertEquals(true, this.gioco.vinta());
	} 
		
	
}