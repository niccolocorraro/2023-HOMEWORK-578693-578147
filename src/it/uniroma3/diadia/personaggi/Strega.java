package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private static final String MESSAGGIO_BUONO = "Salve anche a te, mi stai simpatico, ti porto in un posto migliore";
	private static final String MESSAGGIO_CATTIVO = "Ignorandomi hai richiesto il peggio, ed il peggio ti sarà dato "; 
	private static final String PRESENTAZIONE = "io sono la strega, comportati bene o vedrai";
	private List<Attrezzo> attrezzi;
	
	public Strega(String nome) {
		super(nome, PRESENTAZIONE);
		this.attrezzi = new ArrayList<>();
		
	}
	 
	@Override
	public String agisci(Partita partita) { 
		String msg;
		if (this.haSalutato()) {
			Stanza aMax = null;
			for(Stanza d: partita.getLabirinto().getStanzaCorrente().getDirezioni().values()) {
				if(aMax == null)
					aMax = d;
				if(d.getAttrezzi().size() > aMax.getAttrezzi().size())
					aMax = d;
				}
			partita.getLabirinto().setStanzaCorrente(aMax);
			msg = MESSAGGIO_BUONO;
		} 
		else { 
			Stanza aMin = null;
			for(Stanza d: partita.getLabirinto().getStanzaCorrente().getDirezioni().values()) {
				if(aMin == null)
					aMin = d;
				if(d.getAttrezzi().size() < aMin.getAttrezzi().size())
					aMin = d;
				}
			partita.getLabirinto().setStanzaCorrente(aMin);
			msg = MESSAGGIO_CATTIVO; 
			} 
	
		return msg; 
		}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		
		this.attrezzi.add(attrezzo); 
		String msg = "grazie AHAHAHH";
		return msg;
	}
}
