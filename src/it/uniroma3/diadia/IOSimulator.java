package it.uniroma3.diadia;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IOSimulator implements IO {

	private IO io= new IOConsole();   
	private Map<String,String> mappaComandi;
	
	public IOSimulator() {
		this.mappaComandi = new HashMap<>();
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		io.mostraMessaggio(this.mappaComandi.get(messaggio));
		
	}

	

	@Override
	public String leggiRiga(Scanner s) {
		// TODO Auto-generated method stub
		return null;
	}

}
