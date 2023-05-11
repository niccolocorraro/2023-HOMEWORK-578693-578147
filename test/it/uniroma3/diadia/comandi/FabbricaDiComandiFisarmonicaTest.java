package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {

	Comando comandoAiuto;
	Comando comandoVai;
	Comando comandoFine;
	Comando comandoGuarda;
	Comando comandoNonValido;
	Comando comandoPosa;
	Comando comandoPrendi;
	
	@BeforeEach
	void setUp() {
		comandoAiuto = new FabbricaDiComandiFisarmonica().costruisciComando("aiuto", null);
		comandoVai = new FabbricaDiComandiFisarmonica().costruisciComando("vai nord", null);
		comandoPrendi = new FabbricaDiComandiFisarmonica().costruisciComando("prendi osso", null);
		comandoPosa = new FabbricaDiComandiFisarmonica().costruisciComando("posa osso", null);
		comandoFine = new FabbricaDiComandiFisarmonica().costruisciComando("fine", null);
		comandoGuarda = new FabbricaDiComandiFisarmonica().costruisciComando("guarda", null);
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
		assertTrue(comandoAiuto.getNome().equals("aiuto"));
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
