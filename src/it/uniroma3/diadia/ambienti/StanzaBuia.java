package it.uniroma3.diadia.ambienti;


public class StanzaBuia extends Stanza {
	
	private String attrezzo;
	
	public StanzaBuia(String nome , String atr) {
		super(nome);  
		this.attrezzo = atr;   
	}
	
	@Override
	 public String getDescrizione() {
		if(this.hasAttrezzo(attrezzo))
			 return super.getDescrizione();
		else
			return "qui c'è buio pesto";
    }

  
	
}
