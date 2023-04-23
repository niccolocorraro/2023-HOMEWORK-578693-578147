package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza  
 * @version base
 */

public class Partita {
 
	private Giocatore giocatore;
	private Labirinto labirinto;
	private boolean finita; 
	private IO console;
	
	public Partita() {
		this.labirinto = new Labirinto();
		this.finita = false;
		this.giocatore = new Giocatore();
		this.console = new IOConsole();
	}
	 
	/** 
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente()== this.labirinto.getStanzaVincente();
	} 

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || this.giocatore.getCfu()==0;
	}
  
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	

	

	public boolean giocatoreIsVivo() {
		boolean check= true;
		if(this.giocatore.getCfu()==0) {
			check=false;
		} 
		return check;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
}
