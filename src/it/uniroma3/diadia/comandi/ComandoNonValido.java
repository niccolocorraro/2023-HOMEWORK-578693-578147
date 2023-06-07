package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	private IO io= new IOConsole();  
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("comando sconosciuto");
		io.mostraMessaggio("per la lista dei comandi digitare 'aiuto'");
		 
	}
 

	@Override
	public String getNome() {
		return "nonValido";
	}

	

	

}
