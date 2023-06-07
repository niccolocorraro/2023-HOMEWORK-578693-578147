package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;

/*
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra  
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */

public class ComandoVai extends AbstractComando{

	private String direzione; 
	private IO io= new IOConsole();  
	 //ho aggiunto il metodo isStringDirezione per provare a risolvere con gli enum
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		if((this.direzione == null) || !isStringDirezione(this.direzione) ) {
			io.mostraMessaggio("Impossibile proseguire");
			return; 
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza==null) {
			io.mostraMessaggio("Direzione inesistente");
			return;
		}
		
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
	} 

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
		
	}

	@Override 
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParametro() {
		return direzione;
	}

	 
	public boolean isStringDirezione(String dir ) {
		try {
			Direzione.valueOf(dir);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		} 
	}

	
	 
	
	
}
