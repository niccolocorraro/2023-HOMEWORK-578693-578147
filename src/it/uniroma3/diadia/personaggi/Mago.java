package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " + "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla..."; 
	private static final String PRESENTAZIONE = "io sono il mago dei maghi";
	private Attrezzo attrezzo;
	
	
	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE); 
		this.attrezzo = attrezzo; 
	}
	
	@Override  
	public String agisci(Partita partita) {  
		String msg;
		if (this.attrezzo!=null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo); 
			this.attrezzo = null; 
			msg = MESSAGGIO_DONO;
		}  
		else { 
			msg = MESSAGGIO_SCUSE; } 
		return msg; }

	@Override 
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo a = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso()/2); 
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);
		String msg = "un regalo tu mi hai fatto e di peso io te lo dimezzo";
		return msg;
	}
}