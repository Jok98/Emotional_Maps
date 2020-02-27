package Soluzione;
import java.awt.Frame;
import java.io.*;

/**
 * 
 * @author Matteo Moi matricola n°737574
 *
 */
public class LogIn {

	static Frame error = new Frame();
	
public static void main (String[] args) {


	
}
	/**
	 * Date due stringe in input al metodo esse vengono comparate<br>
	 * in particolare questo metodo viene utilizzato per verificare i dati inseriti dall'utente per il login che vengono comparati con l'array di char contenente il testo del file<br>
	 * @param str contiene l'array da comparare con strFile
	 * @param strFile indica l'array contenente carattere per carattere tutto il testo del file utente
	 * @param length indica la lunghezza della stringa(dato) inserito dall'utente
	 * @param position indica la posizione nel file utente dei dati da comparare
	 * @return accesso indica se i due array coincidono(accesso = true) oppure no (accesso = false)
	 */
	
	public Boolean arrayload(char[] str, char[] strFile, int length, int position ) {
		Boolean accesso = null ;
		
		for(int i=0; i<length; i++) {
			char x = str[i];
			char y = strFile[i+position];
		
			if(x==y){
				
				accesso = true;
				
			} else {
				
				
				accesso = false;
				break;
			}
			
			}
		return accesso; 
			
		}
		
	/**
	 * Il metodo  converte in un array di char la stringa data in input
	 * @param s stringa data in input al metodo
	 * @return c
	 */
	public char[] estrazione (String s) {
		char[] c = new char [s.length()];
		for(int i= 0; i<s.length();i++) {
			c[i] = s.charAt(i);

		}
		
		return c;
	}
	
	/**
	 * Il metodo  va iscrivere in un array di char il contenuto del file indicato dal pathfile
	 * @param pathfile path che indica la locazione del file
	 * @param file indica il file che si vuole leggere
	 * @return strFile 
	 */
	public char[] rdFile (String pathfile, File file){
		
		char[] strFile = new char[(int) file.length()];
		
		if(file.exists()) {
			
			try {
				FileReader rd = new FileReader(file);
				int size = rd.read(strFile);
				
				for (int i = 0 ; i<size ; i++) {
					char x = strFile[i];
					strFile[i]= x;

					
				}
				rd.close();
				
			} catch (FileNotFoundException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		}
			
		return strFile;
	}


}
