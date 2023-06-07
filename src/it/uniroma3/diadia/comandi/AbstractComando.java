package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	private String parametro;
 
	public abstract void esegui(Partita partita);
	
	public abstract String getNome();
	
	public String getParametro() {
		return this.parametro;
	};

	public void setParametro(String parametro) {
		this.parametro = parametro;
	};
	
}  
 