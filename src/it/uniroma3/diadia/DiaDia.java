package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia  {

	private IO io ; 
	private Partita partita; 

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	public DiaDia(Labirinto labirinto ,IO io) throws IOException { 
		this.partita = new Partita(labirinto);
		this.io= io;
	}
   
	public void gioca() {
		String istruzione; 
		Scanner s = new Scanner(System.in);

		 io.mostraMessaggio(MESSAGGIO_BENVENUTO); 
		do	{
			io.mostraMessaggio("-------------------------------------------------------------------");
			istruzione = io.leggiRiga(s);
			io.mostraMessaggio("---------------------------------------------------------------------------------");
		}
		while (!processaIstruzione(istruzione));
		 
	}     

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();

		comandoDaEseguire = factory.costruisciComando(istruzione, this.io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta()) 
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo()) 
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
	
	
	public static void main(String[] argc) throws FormatoFileNonValidoException, IOException {
			    
		IO io = new IOConsole();
		Labirinto labirinto = Labirinto.newBuilder("resources/labirinto.txt");
		
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}
}