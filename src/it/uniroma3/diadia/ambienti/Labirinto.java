package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	
	protected Stanza stanzaCorrente;
	protected Stanza stanzaVincente;
	protected IO io = new IOConsole();
	
 
	private Labirinto() {
		this.stanzaCorrente = null;  
		this.stanzaVincente = null;
	}
	   
 
	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException { 
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaCorrente = c.getStanzaIniziale(); 
		this.stanzaVincente = c.getStanzaVincente();
	}
	
	public static Labirinto newBuilder2() throws FileNotFoundException, FormatoFileNonValidoException {
        return new Labirinto();
    } 
	 
	public static Labirinto newBuilder(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
        return new Labirinto(nomeFile);
    } 
	
	public static LabirintoBuilder newBuilder() throws FileNotFoundException, FormatoFileNonValidoException {
        return new LabirintoBuilder();
    }
	 
	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}   
	
	public Stanza getStanzaVincente() { 
		return stanzaVincente;
	}
	  
	public void setStanzaCorrente(Stanza stanzacorrente) {
		this.stanzaCorrente = stanzacorrente;
	}
	
	public void setStanzaVincente(Stanza stanzavincente) {
		this.stanzaVincente = stanzavincente;
	}

	
	public static class LabirintoBuilder {

			private Labirinto labirinto;
			private Map<String,Stanza> labBu;
			private Stanza lastAdd;

		   
			private  LabirintoBuilder() throws FileNotFoundException, FormatoFileNonValidoException { 
				this.labBu = new HashMap<>();
				this.labirinto = new Labirinto();
			} 
			
			 
			public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
				Stanza stanzaCorrente = new Stanza(nomeStanza);
				this.labirinto.setStanzaCorrente(stanzaCorrente);
				setLastAdd(stanzaCorrente);
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
				setLastAdd(stanzaN);
				return this; 
			}
			   
			public LabirintoBuilder addStanzaBloccata(String nomStanza, String atr, String direzioneOut) {
				Stanza stanzaN = new StanzaBloccata(nomStanza, atr , direzioneOut);
				this.labBu.put(nomStanza, stanzaN);
				setLastAdd(stanzaN);  
				return this;
			}
			
			public LabirintoBuilder addStanzaBuia(String nomStanza, String attrezzo) {
				Stanza stanzaN = new StanzaBuia(nomStanza,attrezzo);
				this.labBu.put(nomStanza, stanzaN);
				setLastAdd(stanzaN);
				return this;
			}
			 
			public LabirintoBuilder addStanzaMagica(String nomStanza) {
				Stanza stanzaN = new StanzaMagica(nomStanza);
				this.labBu.put(nomStanza, stanzaN);
				setLastAdd(stanzaN); 
				return this;  
			} 
			 
			public LabirintoBuilder addStanzaMagica(String nomStanza,int soglia) {
				Stanza stanzaN = new StanzaMagica(nomStanza,soglia);
				this.labBu.put(nomStanza, stanzaN);
				setLastAdd(stanzaN);
				return this;
			}

			public LabirintoBuilder addAttrezzo(String nomeA, int peso, String nomeStanza) {
				Attrezzo a = new Attrezzo(nomeA,peso);
				this.labBu.get(nomeStanza).addAttrezzo(a);
				return this;
			} 
			
			public LabirintoBuilder addMago(String nome, String nomeA, int pesoA, String nomeStanza) {
				Attrezzo a = new Attrezzo(nomeA,pesoA);
				AbstractPersonaggio mago = new Mago(nome,a);
				this.labBu.get(nomeStanza).setPersonaggio(mago);;
				return this;
			}
			
			public LabirintoBuilder addStrega(String nome, String nomeStanza) {
				AbstractPersonaggio Strega = new Strega(nome);
				this.labBu.get(nomeStanza).setPersonaggio(Strega);;
				return this; 
			}
			
			public LabirintoBuilder addCane(String nome, String nomeA, int pesoA, String nomeStanza) {
				Attrezzo a = new Attrezzo(nomeA,pesoA);
				AbstractPersonaggio cane = new Cane(nome,a);
				this.labBu.get(nomeStanza).setPersonaggio(cane);;
				return this;
			}

			public Labirinto getLabirinto() {
				return this.labirinto;
			}
			 
			public Map<String,Stanza> getListaStanze(){
				return labBu;
			}


			public Stanza getLastAdd() {
				return lastAdd;
			}


			public void setLastAdd(Stanza lastAdd) {
				this.lastAdd = lastAdd;
			} 

	}
	
	
	
}
