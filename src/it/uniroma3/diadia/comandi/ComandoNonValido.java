package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {

	private IO io= new IOConsole();  
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("comando sconosciuto");
		io.mostraMessaggio("per la lista dei comandi digitare 'aiuto'");
		 
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return "nonValido";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
