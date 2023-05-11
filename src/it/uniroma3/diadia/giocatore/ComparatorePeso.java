package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatorePeso  implements Comparator<Attrezzo>{

	
	@Override
	public int compare(Attrezzo o1, Attrezzo o2) { 
		
		if(o1.getPeso()<o2.getPeso())
			return -1;
		if(o1.getPeso()>o2.getPeso())
			return 1;
		
		return o1.getNome().compareTo(o2.getNome());
	}
	/*
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		return a1.getPeso() - a2.getPeso();
	}
	*/
}
