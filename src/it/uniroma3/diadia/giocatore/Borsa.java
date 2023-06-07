package it.uniroma3.diadia.giocatore;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Borsa  { 
	public final static int DEFAULT_PESO_MAX_BORSA = 10; 
	private HashMap<String,Attrezzo> attrezzi;
	private int pesoMax; 
	private Properties properties;

 
	public Borsa() throws IOException {
		this.properties = new Properties();
		this.pesoMax = creaPesoMax();
		this.attrezzi = new HashMap<>(); 
	}

	public Borsa(int pesoMax) { 
		this.pesoMax = pesoMax; 
		this.attrezzi = new HashMap<>(); 
	}

	/*  
	 * Aggiunge un attrezzo alla borsa
	 * @return true se lo inserisce
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso() +attrezzo.getPeso() > this.getPesoMax())
			return false; 
		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		return true;
	}

	/* 
	 * @return il peso massimo della borsa
	 */
	public int getPesoMax() { 
		return pesoMax;
	}

	/*
	 * @return l'attrezzo cercato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) { 
		return this.attrezzi.get(nomeAttrezzo);
	}

	/*
	 * @return il peso contenuto nella borsa 
	 */
	public int getPeso() { 
		int peso = 0;
		for (Attrezzo a : this.attrezzi.values()) 
			peso += a.getPeso();

		return peso; 
	}  
	
	public int creaPesoMax()throws IOException {
		FileInputStream file = new FileInputStream("resources/diadia.properties");
		properties.load(file);
		int i = Integer.parseInt(properties.getProperty("PESOBORSA"));
		return i;
	} 

	/*
	 * @return true se la borsa è vuota
	 */
	public boolean isEmpty() { 
		return this.attrezzi.isEmpty();
	}

	/*
	 * @return true se la borsa contiene quell'attrezzo
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) { 
		return this.attrezzi.containsKey(nomeAttrezzo);
	}


	public Attrezzo removeAttrezzo(String nomeAttrezzo) { 
		return this.attrezzi.remove(nomeAttrezzo);
	} 

	public String toString() {
		StringBuilder s = new StringBuilder(); 

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): "); 
			s.append(this.attrezzi.values());
		} 
		else 
			s.append("Borsa vuota");  

		return s.toString(); 
	}

	//funzione che restituisce la lista degli attrezzi nella 
	//borsa ordinati per peso e quindi, a parità di peso, per nome

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){

		List<Attrezzo> listaAttrezzi = new ArrayList<>(attrezzi.values());
		Comparator<Attrezzo> comp = new ComparatorePeso(); 
		Collections.sort(listaAttrezzi,comp);
		return listaAttrezzi;
	} 
 
	//restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {

		SortedSet<Attrezzo> attrezziOrdinati = new TreeSet<>(new ComparatorePerNome());
		attrezziOrdinati.addAll(this.attrezzi.values());
		return attrezziOrdinati; 
	}
	 
	//restituisce l'insieme gli attrezzi nella borsa ordinati per peso e quindi,
	//a parità di peso, per nome
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){

		SortedSet<Attrezzo> attrezziOrdinati = new TreeSet<>(new ComparatorePeso());
		attrezziOrdinati.addAll(this.attrezzi.values());
		return attrezziOrdinati;
	}


	 
	public Map<Integer, Set<Attrezzo>> raggruppaAttrezziPerPeso() {
		//creo mappa
	    Map<Integer, Set<Attrezzo>> mappaRaggruppata = new HashMap<>();

	    // Scorro tutti gli attrezzi nella mappa originale
	    for (Attrezzo attrezzo : attrezzi.values()) {
	        int peso = attrezzo.getPeso();
	        Set<Attrezzo> insiemeAttrezzi = mappaRaggruppata.get(peso);

	        // Se non esiste ancora un insieme di attrezzi con il peso corrente,
	        // lo creo e lo aggiungo alla mappa raggruppata
	        if (insiemeAttrezzi == null) {
	            insiemeAttrezzi = new HashSet<>();
	            mappaRaggruppata.put(peso, insiemeAttrezzi);
	        }

	        // Aggiungo l'attrezzo all'insieme degli attrezzi con lo stesso peso
	        insiemeAttrezzi.add(attrezzo);
	    }

	    return mappaRaggruppata;
	}


}


