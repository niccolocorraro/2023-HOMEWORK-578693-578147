package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.*;

public interface FabbricaDiComandi {
	public AbstractComando costruisciComando(String istruzione, IO console) throws Exception;
}
