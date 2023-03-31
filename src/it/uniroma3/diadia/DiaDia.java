package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Stanza;


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

public class DiaDia {

	private IOConsole console; 
	
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi" , "posa"};

	private Partita partita;

	public DiaDia() { 
		this.partita = new Partita();
		this.console= new IOConsole();
	}

	public void gioca() {
		String istruzione; 
		
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
		
	}   

 
	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
			
		if(comandoDaEseguire.getNome()==null) 
			console.mostraMessaggio("comando sconosciuto");
		
		else {
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))
				this.vai(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("aiuto"))
				this.aiuto();
			else if (comandoDaEseguire.getNome().equals("prendi"))
				this.prendi(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals("posa"))
				this.posa(comandoDaEseguire.getParametro());
			else if (comandoDaEseguire.getNome().equals(null))
				this.fine(); 
			else
				console.mostraMessaggio("comando sconosciuto");
		}
			if (this.partita.vinta()) {
				console.mostraMessaggio("Hai vinto!"); 
				return true; 
			} else
				return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder s = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) {
			s.append(elencoComandi[i] + " ");
		}
		this.console.mostraMessaggio(s.toString());
	} 

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra  
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare?"); 
		Stanza prossimaStanza = null; 
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) 
			console.mostraMessaggio("Stanza inesistente !"); 
		else { 
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--); 
		} 
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione()); 
	} 
	
	/*
	 * Comando posa : gli attrezzi posati vengono rimossi dalla borsa e aggiunti alla stanza
	 */
	
	public void posa(String nomeAttrezzo) {
		if(nomeAttrezzo == null) 
			console.mostraMessaggio("Che attrezzo vuoi posare?");
		else if(!this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))
			console.mostraMessaggio("Attrezzo non presente in borsa");
		else if(!this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo)))
			console.mostraMessaggio("Impossibile posare l'attrezzo: stanza piena"); 
		else {
			this.console.mostraMessaggio(nomeAttrezzo + " posato"); 
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		}

	} 
	
	/*
	 * Comando prendi: gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa
	 */

		public void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo == null) 
			console.mostraMessaggio("Che attrezzo vuoi prendere?");
		else if(!this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo))
			console.mostraMessaggio("Attrezzo non presente in Stanza");
		else if(!this.partita.getGiocatore().getBorsa().addAttrezzo(this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo)))
			console.mostraMessaggio("Impossibile prendere l'attrezzo: Borsa piena"); 
		else {
			this.console.mostraMessaggio("Attrezzo '"+nomeAttrezzo +"' preso, ora la borsa pesa "+this.partita.getGiocatore().getBorsa().getPeso()+"Kg" ); 
			this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo));
		}
	}
	
		
	/** 
	 * Comando "Fine". 
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}