package it.uniroma3.diadia.comandi;
import java. util. Scanner; 
import it.uniroma3.diadia.IO;
 
public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	@Override public AbstractComando costruisciComando(String istruzione,IO io) { 
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null; 
			String parametro = null;
			AbstractComando comando = null;
			
			if (scannerDiParole.hasNext()) 
				nomeComando = scannerDiParole.next();
			//prima parola: nome del comando 
			if (scannerDiParole.hasNext())  
				parametro = scannerDiParole.next();
			//seconda parola: eventuale parametro
			try {  
				String nomeClasse = "it.uniroma3.diadia.comandi.Comando"; 
				nomeClasse += Character.toUpperCase(nomeComando.charAt(0)); 
				nomeClasse += nomeComando.substring(1); 
				comando = (AbstractComando)Class.forName(nomeClasse).getDeclaredConstructor().newInstance(); 
				comando.setParametro(parametro); } 
			catch (Exception e) { 
					comando = new ComandoNonValido(); 
					
			} 
			return comando;
		}  
		} 
	}  