package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;


/*
 * test che coontrolla addAttrezzo , getPeso ed removeAttrezzo
 */

public class BorsaTest {
 
	private Borsa borsa;
	private Borsa sacca;
	private Attrezzo attrezzo;
	private Attrezzo utensile;
	private Attrezzo arma;
	private Attrezzo accessorio;
	private Borsa set;
	private Attrezzo a1;
	private Attrezzo b1;
	private Attrezzo c1;
	
	
	@BeforeEach 
	public void setUp() { 
		this.sacca = new Borsa();
		this.borsa = new Borsa();
		this.accessorio = new Attrezzo("accessorio", 3);
		this.attrezzo = new Attrezzo("attrezzo", 6);
		this.utensile = new Attrezzo("utensile", 25);
		this.arma = new Attrezzo("arma", 17);
		
		this.set = new Borsa();
		this.b1 = new Attrezzo("b1", 2);
		this.a1 = new Attrezzo("a1", 2);
		this.c1 = new Attrezzo("c1",1);
		this.set.addAttrezzo(b1);
		this.set.addAttrezzo(a1);
		this.set.addAttrezzo(c1);
		
		this.sacca.addAttrezzo(attrezzo);
		this.sacca.addAttrezzo(accessorio);
		
	}
	 
	@AfterEach
	public void tearDown() {
		this.accessorio = null;
		this.sacca = null;
		this.attrezzo = null;
		this.borsa = null;
		this.utensile = null;
		this.arma= null;
		this.set = null;
		this.a1 = null;
		this.b1 = null;
		this.c1 = null;
	}
	
	/*
	 * test per addAttrezzo
	 */
	@Test //test con l'attrezzo 
	public void testAddAttrezzo1() {
		assertEquals(true, this.borsa.addAttrezzo(attrezzo));
	}
	@Test //test per un attrezzo troppo pesante
	public void testAddAttrezzo2() {
		assertEquals(false, this.borsa.addAttrezzo(utensile));
	}
	
	@Test //test per un attrezzo che sommato agli altri è troppo pesante per entrare in borsa
	public void testAddAttrezzo3() {
		assertEquals(false, this.borsa.addAttrezzo(arma));
	}
	
	/*
	 * test per getPeso
	 */
	@Test // test per una borsa con 2 oggetti leggeri
	public void testGetPeso1() {
		assertEquals(9  , this.sacca.getPeso());
	}
	
	@Test //test per una borsa vuota
	public void testGetPeso2() {
		assertEquals(0 , this.borsa.getPeso());
	} 
	
	/*
	 * test per removeAttrezzo 
	 */
	@Test //rimozione attrezzo 
	public void testRemoveAttrezzo1() {
		assertEquals(accessorio, this.sacca.removeAttrezzo("accessorio"));
	}
	 
	@Test //rimozione attrezzo inesistente
	public void testRemoveAttrezzo2() {
		assertEquals(null, this.sacca.removeAttrezzo("pistola"));
	}
	
	@Test //rimozione attrezzo in una borsa con piu attrezzi
	public void testRemoveAttrezzo3() {
		assertEquals(attrezzo, this.sacca.removeAttrezzo("attrezzo"));
	}

	
	/*
	 * test per hasAttrezzo
	 */
	
	@Test //Verifica che l'attrezzo "attrezzo" sia nella borsa
	void testHasAttrezzo1() {
		assertEquals(true, this.sacca.hasAttrezzo("attrezzo"));
	}


	@Test //Verifica che l'attrezzo "utensile" non sia nella borsa
	void testHasAttrezzo2() {
		assertEquals(false, this.borsa.hasAttrezzo("accessorio"));
	}
	
	@Test //verifica che nonostante due attrezzi abbiano lo stesso peso ma nome diverso si differenzino
	void testGetSortedSetOrdinatoPerPeso(){
		assertEquals("[c1 (1kg), a1 (2kg), b1 (2kg)]", this.set.getSortedSetOrdinatoPerPeso().toString());
	}
	
	@Test //verifica di ordinamento per nome
	void testGetContenutoOrdinatoPerNome() {
		assertEquals("[a1 (2kg), b1 (2kg), c1 (1kg)]",this.set.getContenutoOrdinatoPerNome().toString());
	}
	
	@Test //verifica l'ordinamento per peso, ed in caso per nome
	void testGetContenutoOrdinatoPerPeso() {
		assertEquals("[c1 (1kg), a1 (2kg), b1 (2kg)]", this.set.getContenutoOrdinatoPerPeso().toString());
	}
	
	@Test //ragguppa attrezzi dello stesso peso
	void testRaggruppaAttrezziPerPeso() {
		assertEquals("{1=[c1 (1kg)], 2=[a1 (2kg), b1 (2kg)]}", this.set.raggruppaAttrezziPerPeso().toString());
	}
	
	

}
