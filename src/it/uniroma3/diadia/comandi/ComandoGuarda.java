package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	private IO io= new IOConsole();   

	@Override
	public void esegui(Partita partita) {
 
		io.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("Numero di cfu : "+partita.getGiocatore().getCfu());
		io.mostraMessaggio("Oggetti nella borsa : "+partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome());
		io.mostraMessaggio("Peso della borsa : "+partita.getGiocatore().getBorsa().getPeso());
	}    

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}




}
