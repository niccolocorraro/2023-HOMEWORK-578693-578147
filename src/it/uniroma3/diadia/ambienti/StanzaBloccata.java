package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	private String dirBloccata;
	private String attrezzoUnl;
	
	public StanzaBloccata(String nome, Attrezzo chiave, String direzioneOut){
		super(nome);
		this.attrezzoUnl = chiave.getNome();
		this.dirBloccata=direzioneOut;
	}
	  
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		
		if(!this.hasAttrezzo(attrezzoUnl) ) {
			 return this;}
		else
			return super.getStanzaAdiacente(dir);}
	

	@Override 
	public String getDescrizione(){
		if(!hasAttrezzo(attrezzoUnl))
			 return super.getDescrizione()+this.getAttrezzoUnl()+this.dirBloccata;
		else
			return super.getDescrizione();
	}

	public String getDirBloccata() {
		return dirBloccata;
	}

	public void setDirBloccata(String dirBloccata) {
		this.dirBloccata = dirBloccata;
	}

	public String getAttrezzoUnl() {
		return attrezzoUnl;
	}

	public void setAttrezzoUnl(String attrezzoUnl) {
		this.attrezzoUnl = attrezzoUnl;
	}

		
	
}