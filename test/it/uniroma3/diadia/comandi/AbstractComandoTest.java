package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class AbstractComandoTest {


	private AbstractComando comandoAiuto;
	private AbstractComando comandoVai;
	private AbstractComando comandoFine;
	private AbstractComando comandoGuarda;
	//private Comando comandoNonValido;
	private AbstractComando comandoPosa;
	private AbstractComando comandoPrendi;
	private IO io; 
	
	
	@BeforeEach  
	void setUp() {
		this.io = new IOConsole();
		comandoAiuto = new FabbricaDiComandiRiflessiva().costruisciComando("aiuto", io);
		comandoVai = new FabbricaDiComandiRiflessiva().costruisciComando("vai nord", io);
		comandoPrendi = new FabbricaDiComandiRiflessiva().costruisciComando("prendi osso", io);
		comandoPosa = new FabbricaDiComandiRiflessiva().costruisciComando("posa osso", io);
		comandoFine = new FabbricaDiComandiRiflessiva().costruisciComando("fine", io);
		comandoGuarda = new FabbricaDiComandiRiflessiva().costruisciComando("guarda", io);
	} 

	@AfterEach
	void tearDown()  {
		comandoAiuto = null;
		comandoVai= null;
		comandoPrendi= null; 
		comandoPosa= null;
		comandoFine= null;
		comandoGuarda = null;
	}

	@Test
	void testAiuto() {
		assertEquals("aiuto",comandoAiuto.getNome());
	}
	
	@Test
	void testVai() {
		assertTrue(comandoVai.getNome().equals("vai"));
	}
	
	@Test
	void testVaiPar() {
		assertTrue(comandoVai.getParametro().equals("nord"));
	}
	
	@Test
	void testPrendi() {
		assertTrue(comandoPrendi.getNome().equals("prendi"));
	}

	@Test
	void testPrendiPar() {
		assertTrue(comandoPrendi.getParametro().equals("osso"));
	}
	
	@Test
	void testPosa() {
		assertTrue(comandoPosa.getNome().equals("posa"));
	}
	
	@Test
	void testPosaPar() {
		assertTrue(comandoPosa.getParametro().equals("osso"));
	}
	
	@Test
	void testFine() {
		assertTrue(comandoFine.getNome().equals("fine"));
	}
	
	@Test
	void testGuarda() {
		assertTrue(comandoGuarda.getNome().equals("guarda"));
	}
	

	
	
	
}
