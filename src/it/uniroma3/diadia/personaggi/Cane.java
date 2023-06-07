package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private Attrezzo attrezzo;
	private static final String PRESENTAZIONE = "GRRRRRR";
	
	public Cane(String nome, Attrezzo a) {
		super(nome, PRESENTAZIONE); 
		this.attrezzo = a;
	} 
	
	@Override
	public String agisci(Partita partita) { 
		String msg;
		msg = "GRRR *morso* cfu --";
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);    
		return msg; 
		}

	@Override
	public String riceviRegalo(Attrezzo cibo, Partita partita) {
		if(cibo.getNome().equals("croccantini")) {
			String msg = "grazie per il mio cibo preferito , *cade oggettos*";
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
			return msg;
		}
		else 
			return agisci(partita);
	}
}