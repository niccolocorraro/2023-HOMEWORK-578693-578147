package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.HashMap;
import it.uniroma3.diadia.attrezzi.*;


public class LabirintoBuilder extends Labirinto {

	private Labirinto labirinto;
	private Map<String,Stanza> labBu;
	private Stanza lastAdd;
  
	public LabirintoBuilder() { 
		this.labBu = new HashMap<>();
		 this.labirinto = new Labirinto();
	}  

	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza stanzaCorrente = new Stanza(nomeStanza);
		this.labirinto.setStanzaCorrente(stanzaCorrente);
		lastAdd = stanzaCorrente;
		this.labBu.put(nomeStanza, stanzaCorrente);
		return this;  
	}


	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza stanzaVincente = new Stanza(nomeStanza);
		this.labirinto.setStanzaVincente(stanzaVincente);
		this.labBu.put(nomeStanza, stanzaVincente);
		return this;
	}
  
  
 
	public LabirintoBuilder addAdiacenza(String stanzaBase, String stanzaAdi, String direzione) {
		if(labBu.containsKey(stanzaBase) && labBu.containsKey(stanzaAdi))
			this.labBu.get(stanzaBase).impostaStanzaAdiacente(direzione, labBu.get(stanzaAdi));
		return this;  
	}        
 
	public LabirintoBuilder addStanza(String nomStanza) {
		Stanza stanzaN = new Stanza(nomStanza);
		this.labBu.put(nomStanza, stanzaN);
		lastAdd = stanzaN;
		return this; 
	}
	   
	public LabirintoBuilder addStanzaBloccata(String nomStanza, String atr, String direzioneOut) {
		Stanza stanzaN = new StanzaBloccata(nomStanza, atr , direzioneOut);
		this.labBu.put(nomStanza, stanzaN);
		lastAdd = stanzaN;  
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nomStanza, String attrezzo) {
		Stanza stanzaN = new StanzaBuia(nomStanza,attrezzo);
		this.labBu.put(nomStanza, stanzaN);
		lastAdd = stanzaN;
		return this;
	}
	 
	public LabirintoBuilder addStanzaMagica(String nomStanza) {
		Stanza stanzaN = new StanzaMagica(nomStanza);
		this.labBu.put(nomStanza, stanzaN);
		lastAdd = stanzaN; 
		return this; 
	} 
	 
	public LabirintoBuilder addStanzaMagica(String nomStanza,int soglia) {
		Stanza stanzaN = new StanzaMagica(nomStanza,soglia);
		this.labBu.put(nomStanza, stanzaN);
		lastAdd = stanzaN;
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeA, int peso) {
		Attrezzo a = new Attrezzo(nomeA,peso);
		this.labBu.get(lastAdd.getNome()).addAttrezzo(a);
		return this;
	} 

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	 
	public Map<String,Stanza> getListaStanze(){
		return labBu;
	} 
	
	
	
	/*
	public Map<String,Stanza> getMapStanzeAdiacenti(){
		Map<String,Stanza> lista = new HashMap<>();
		
		for(Stanza stanza : labBu.values()) {
			if
		}
	}
	*/
	

}