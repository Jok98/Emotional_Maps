package Soluzione;
import java.awt.Frame;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * @author Matteo Moi matricola n°737574
 */
public class Registrazione  {

	static String path = "C:\\EmotionalMapsFile\\Utenti\\";
	
	static Frame message = new Frame();
	
	public static void main (String[] args) {


		
	}
	/**
	 * Creo nuova directory con percorso = a path
	 * @param path viene utilizzato per creare la directory
	 * Se il path è già esistente non viene ricreato
	 */
	
	public void newDir(String path) {
	File dir = new File(path);
	if(!dir.exists()) {
		dir.mkdir();
	}
	}
	
	
	/**
	 * creo nuovo file con percorso = a pathfile
	 * @param pathfile
	 * @param file
	 */
	
	public void newFile(File file) {
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();
				
			} catch (IOException e) {
		
				e.printStackTrace();
			}	
		}	
	}
	
	/**
	 * Il metodo crea (se non esiste) il file utente nel quale verrano salvati i dati ad esso collegati
	 * @param pathfile
	 * @param file
	 * @param u
	 * @param ctrlfile
	 */
	public void wrFile (String pathfile, File file, Utente u, Registrazione ctrlfile) {

			if(!file.exists()) {
			ctrlfile.newFile( file);
			
			
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(file));
			wr.write("ID : " + u.ID +  " || " +   "Password : " + u.Password + " || " +"Nome: " + u.Nome + " || " + "Cognome: " + u.Cognome );
			wr.newLine();
			wr.close();
			//Finita l'iscrizione dell'utente viene mostrato a schermo questo messaggio
			JOptionPane.showMessageDialog(message, "Utente creato, ID : " + u.ID);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//Se il file utente è già esistente viene mostrato a schermo questo messaggio
	}else JOptionPane.showMessageDialog(message, "Utente già esistente.");
	}
}
