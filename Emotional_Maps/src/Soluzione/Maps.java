package Soluzione;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;


/**
 * 
 * @author Matteo Moi matricola n°737574
 *
 */
public class Maps{

	
	static EmotionalMaps m = new EmotionalMaps();
	static Utente u = new Utente();
	static Registrazione r = new Registrazione();
	static LogIn L = new LogIn();
	static Maps map = new Maps();
	static POI poi = new POI();
	static GUI_Registrazione gui_reg = new GUI_Registrazione();
	static GUI_LogIn gui_log = new GUI_LogIn();
	
	static Frame message = new Frame();
	
	public static void main(String[] args) {
		
		
	}
	
	/**
	 * scrive le coordinate nel file utente
	 * @param pathfile indica il path del file utente
	 * @param file indica il file utente
	 * @param coorX indica la longitudine
	 * @param coorY indica la latitudine
	 */
	public void setPosition(String pathfile, File file, int coorX, int coorY ) {
		
		if(file.exists()) {
		try {
			
			LogIn log = new LogIn();
			//richiamo il metodo per leggere i file dalla classe LogIn
			char[] strFile = log.rdFile(pathfile, file);

			//metodo di scrittura file
			BufferedWriter wr = new BufferedWriter(new FileWriter(file));
			//riscrivo il file precedente
			wr.write(strFile);
			wr.newLine();
			//aggiungo le nuove coordinate al file utente
			wr.write("Coordinata X : " + coorX + " || " + "Coordinata Y : " + coorY );
			
			wr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//Se il file non esiste viene mostrato a schermo questo messaggio
	} else 	JOptionPane.showMessageDialog(message,"Il file non esiste");
		
		}

	
	
	
	
	/**
	 * carica la matrice Maps da 0 a 35
	 * @param Maps
	 * @return 
	 * 
	 */
	
		public int[][] setMap(int Maps[][]) {
			//contatore
			int c= 0;
			//caricamento array bi
			for (int j= 0; j<Maps.length; j++){
				
				for(int k = 0; k< Maps.length;k++) {
					
				Maps[j][k] = c++;
				
			}
				
			}
			return Maps;
		}
		
		
		
		/**
		 * 
		 * @param Maps matrice con valori da 0 a 35
		 * @param coorX longitudine
		 * @param coorY latitudine
		 * @param show indica se mostrare o no a schermo i messaggi diretti all'utente sulla sua posizione rispetto ai POI
		 * @return 
		 */
		public int compareCoor(int[][] Maps, int coorX, int coorY,Boolean show) {
			int z = 0;
			
			//verifica range 1^Point
			if((Maps[coorX][coorY]== 0) || (Maps[coorX][coorY]== 1) || (Maps[coorX][coorY]== 6) ||(Maps[coorX][coorY]== 7)) {
				z = 1;
			}
			
			//verifica range 2^Point
			if((Maps[coorX][coorY]== 4) || (Maps[coorX][coorY]== 5) || (Maps[coorX][coorY]== 10) ||(Maps[coorX][coorY]== 11)) {
				z = 2;
			}
			
			//verifica range 3^Point
			if((Maps[coorX][coorY]== 26) || (Maps[coorX][coorY]== 27) || (Maps[coorX][coorY]== 32) ||(Maps[coorX][coorY]== 33)) {
				z = 3;
			}
			
			if(show==true) {
			switch(z) {
			case 0 :
				JOptionPane.showMessageDialog(message,"Non sei nel range di nessun point");
				break;
				
			case 1 :
				JOptionPane.showMessageDialog(message,"Primo Point");
				break;
				
			case 2 :
				JOptionPane.showMessageDialog(message,"Secondo Point");
				break;
				
			case 3 : 
				JOptionPane.showMessageDialog(message,"Terzo Point");
				break;
			
			}
	
			}
		return z;	
		}
	
}
