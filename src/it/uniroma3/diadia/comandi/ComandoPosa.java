package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
 
/*
 * Comando posa : gli attrezzi posati vengono rimossi dalla borsa e aggiunti alla stanza
 */

public class ComandoPosa implements Comando {

	private String nomeAttrezzo;
	private IO io= new IOConsole();   
	 
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null) 
			io.mostraMessaggio("Che attrezzo vuoi posare?");
		else if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))
			io.mostraMessaggio("Attrezzo non presente in borsa");
		else if(!partita.getLabirinto().getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo)))
			io.mostraMessaggio("Impossibile posare l'attrezzo: stanza piena"); 
		else {
			io.mostraMessaggio(nomeAttrezzo + " posato"); 
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		}		 
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
		
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

	

}
