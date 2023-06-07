package it.uniroma3.diadia.ambienti;


public class StanzaBloccata extends Stanza{
	private Direzione dirBloccata;
	private String attrezzoUnl;
	
	public StanzaBloccata(String nome, String chiave, String direzioneOut){
		super(nome);
		this.attrezzoUnl = chiave;
		this.dirBloccata= Direzione.valueOf(direzioneOut);
	} 
	    
	@Override
	public Stanza getStanzaAdiacente(String dir) {
	
	if(dir.equals(dirBloccata.toString())) { 
		if(!this.hasAttrezzo(attrezzoUnl) ) {
			 return this;}
		else
			return super.getStanzaAdiacente(dir);
		}
	else
		return super.getStanzaAdiacente(dir);
	}
 
	@Override  
	public String getDescrizione(){
		if(!this.hasAttrezzo(attrezzoUnl))
			 return super.getDescrizione()+"\nNome attrezzo che sblocca :"+this.getAttrezzoUnl()+"\nDirezione bloccata :"+this.dirBloccata;
		else
			return super.getDescrizione();
	}

	public Direzione getDirBloccata() {
		return dirBloccata;
	}

	public void setDirBloccata(String dirBloccata) {
		this.dirBloccata = Direzione.valueOf(dirBloccata);
	}

	public String getAttrezzoUnl() {
		return attrezzoUnl;
	}

	public void setAttrezzoUnl(String attrezzoUnl) {
		this.attrezzoUnl = attrezzoUnl;
	}

		
	
}