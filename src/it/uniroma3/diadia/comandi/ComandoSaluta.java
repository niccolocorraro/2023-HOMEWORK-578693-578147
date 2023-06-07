package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {
	private IO io = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getLabirinto().getStanzaCorrente().getPersonaggio() != null) {
			io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().saluta());
				
		}
		else
			io.mostraMessaggio("non c'è nessuno qui");
	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
