package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoRegala extends AbstractComando{

	private String nomeAttrezzo;
	private IO io= new IOConsole();   
	
	@Override
	public void esegui(Partita partita) {
		
		if(partita.getLabirinto().getStanzaCorrente().getPersonaggio() != null) {
			if(nomeAttrezzo == null) 
				io.mostraMessaggio("Che attrezzo vuoi posare?");
			else if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))
				io.mostraMessaggio("Attrezzo non presente in borsa");
			else {
				io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo),partita)); 
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			}
		} 
		else { 
			io.mostraMessaggio("nessun personaggio a cui regalare l'oggetto "+nomeAttrezzo);
		}
	}


	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
		
	}

	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

}
