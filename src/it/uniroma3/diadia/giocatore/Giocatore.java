package it.uniroma3.diadia.giocatore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Giocatore {

	private int cfu;
	//static final private int CFU_INIZIALI = 20; 
	private Borsa borsa;
	public Properties properties;

	public Giocatore() throws IOException{
		this.properties = new Properties();
		this.cfu = creaCfu();
		this.borsa = new Borsa();
		
		
	} 
		/* 
		 * retituisce il numero di CFU
		 */
		public int getCfu() {
			return this.cfu;
		}
	
		/* 
		 * imposta il numero di CFU
		 */
		public void setCfu(int cfu) {
			this.cfu = cfu;		
		}	
		
		public Borsa getBorsa() {
			return this.borsa;
		}
		
		public int creaCfu() throws IOException {
			FileInputStream fileInputStream = new FileInputStream("resources/diadia.properties"); 
		    properties.load(fileInputStream); 
		    int i = Integer.parseInt(properties.getProperty("CFU"));
		    return i;
		}
		
		
}
