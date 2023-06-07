package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;


/*
 * Stampa informazioni di aiuto.
 */

public class ComandoAiuto extends AbstractComando {

	private IO io= new IOConsole();  

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi" , "posa", "guarda" , "saluta" , "interagisci", "regala"};
 

	@Override
	public void esegui(Partita partita) {
		StringBuilder s = new StringBuilder();
		for(int i=0; i< elencoComandi.length; i++) {
			s.append(elencoComandi[i] + " ");
		}
		io.mostraMessaggio(s.toString());
	}
 

	@Override
	public String getNome() {
		return "aiuto"; 
	}



}
