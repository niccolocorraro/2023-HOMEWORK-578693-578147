package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;

/*
 * Comando prendi: gli attrezzi presi vengono rimossi dalla stanza e aggiunti alla borsa
 */

public class ComandoPrendi implements Comando{

	private String nomeAttrezzo; 
	private IO io= new IOConsole();  
	
	@Override
	public void esegui(Partita partita) { 
		if(nomeAttrezzo == null) 
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
		else if(!partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo))
			io.mostraMessaggio("Attrezzo non presente in Stanza");
		else if(!partita.getGiocatore().getBorsa().addAttrezzo(partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo)))
			io.mostraMessaggio("Impossibile prendere l'attrezzo: Borsa piena");
		else {
			io.mostraMessaggio("Attrezzo '"+nomeAttrezzo +"' preso, ora la borsa pesa "+partita.getGiocatore().getBorsa().getPeso()+"Kg");
			partita.getLabirinto().getStanzaCorrente().removeAttrezzo(partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo));
		}
	
	}
 
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
		
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}

	
}
