package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzo;
	
	public StanzaBuia(String nome) {
		super(nome); 
		this.nomeAttrezzo = nome;
	}
	
	@Override
	 public String getDescrizione() {
		if(hasAttrezzo(nomeAttrezzo))
			 return super.getDescrizione();
		else
			return "qui c'è buio pesto";
    }

  
	
}
