package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             
	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    
	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";
	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanzeBuie */
	private static final String STANZEBUIE_MARKER = "StanzeBuie: ";    
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanzeMagiche */
	private static final String STANZEMAGICHE_MARKER = "StanzeMagiche: ";    
	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanzeBloccate*/
	private static final String STANZEBLOCCATE_MARKER = "StanzeBloccate: ";    
	/* prefisso di una singola riga di testo contenente tutti i nomi dei Personaggi */
	private static final String PERSONAGGI_MARKER = "Personaggi: ";    


	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11 
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private Stanza stanzaIniziale; 
	private Stanza stanzaVincente;
	private LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder = Labirinto.newBuilder();
	} 
 
	public void carica() throws FormatoFileNonValidoException, FileNotFoundException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiEImpostaUscite();
			this.leggiECreaPersonaggi();

		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}


	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}


	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);

		List<String> result = splitString(nomiStanze);
		for(String nomeStanza : result) {
			builder.addStanza(nomeStanza);
		}
	}


	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanzaBlo = this.leggiRigaCheCominciaPer(STANZEBLOCCATE_MARKER);

		for(String stanze : splitString(specificheStanzaBlo)){
			try (Scanner scannerDiLinea = new Scanner(stanze)) {			

				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le specifiche della stanza"));
				String nomeStanza = scannerDiLinea.next(); 
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("nome stanza "+nomeStanza));
				String attrBloccante = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("nome stanza "+nomeStanza+"l'attrezzo che slocca la stanza "+attrBloccante));
				String dirBloccata = scannerDiLinea.next();


				builder.addStanzaBloccata(nomeStanza, attrBloccante, dirBloccata);
			}
		} 
	}


	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanzaMag = this.leggiRigaCheCominciaPer(STANZEMAGICHE_MARKER);

		List<String> result = splitString(specificheStanzaMag);
		for(String nomeStanza : result) {
			builder.addStanzaMagica(nomeStanza);
		}
	}


	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanzaBuie = this.leggiRigaCheCominciaPer(STANZEBUIE_MARKER);

		for(String stanze : splitString(specificheStanzaBuie)){
			try (Scanner scannerDiLinea = new Scanner(stanze)) {			

				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le specifiche della stanza buia"));
				String nomeStanza = scannerDiLinea.next(); 
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("nome stanza buia "+nomeStanza));
				String attrIlluminante = scannerDiLinea.next();


				builder.addStanzaBuia(nomeStanza, attrIlluminante);
			}
		} 
	}


	private void leggiECreaPersonaggi() throws FormatoFileNonValidoException  {
		String personaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String p : splitString(personaggi)){
			try (Scanner scannerDiLinea = new Scanner(p)) {			

				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le specifiche dei personaggi"));
				String tipoPersonaggio = scannerDiLinea.next(); 
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("tipo personaggio "+tipoPersonaggio));
				if(tipoPersonaggio.equals("Mago")){
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("tipo personaggio"+tipoPersonaggio));
					String nomeAttrezzo = scannerDiLinea.next(); 
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce(" attrezzo da donare "+nomeAttrezzo));
					String pesoAttrezzo = scannerDiLinea.next(); 
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce(" peso attrezzo da donare "+pesoAttrezzo));
					String stanza = scannerDiLinea.next(); 
					
					builder.addMago(tipoPersonaggio, nomeAttrezzo, Integer.parseInt(pesoAttrezzo), stanza);
					} 
				if(tipoPersonaggio.equals("Strega")) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("tipo personaggio"+tipoPersonaggio));
					String stanza = scannerDiLinea.next();  
					
					builder.addStrega(tipoPersonaggio,stanza);
				}
				if(tipoPersonaggio.equals("Cane")) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("tipo personaggio"+tipoPersonaggio));
					String nomeAttrezzoIn = scannerDiLinea.next(); 
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce(" attrezzo che dona "+nomeAttrezzoIn));
					String pesoAttrezzo = scannerDiLinea.next(); 
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce(" peso attrezzo che dona "+pesoAttrezzo));
					String stanza = scannerDiLinea.next(); 
					
					
					builder.addCane(tipoPersonaggio, nomeAttrezzoIn, Integer.parseInt(pesoAttrezzo) , stanza);
				}
			}
		} 
	}


	public static List<String> splitString(String input) {
		String[] tokens = input.split(",");
		List<String> list = new LinkedList<>();
		for (String token : tokens) {
			list.add(token.trim());
		}
		return list;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		//check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		//check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");

		builder.addStanzaIniziale(nomeStanzaIniziale);
		builder.addStanzaVincente(nomeStanzaVincente);
		this.stanzaIniziale = builder.getLabirinto().getStanzaCorrente();
		this.stanzaVincente = builder.getLabirinto().getStanzaVincente();

	}

	
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : splitString(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			builder.addAttrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo),nomeStanza);

		} 
	}

	
	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);


		for(String uscite : splitString(specificheUscite)){
			try (Scanner scannerDiLinea = new Scanner(uscite)) {			

				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, dir);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}


}
