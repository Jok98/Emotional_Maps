package Soluzione;
import java.awt.Frame;
import java.io.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * 
 * @author Matteo Moi matricola n°737574
 *
 */

public class POI {
	
	//Felice
	char F;
	//Arrabbiato
	char A;
	//Sorpreso
	char S;
	//Triste
	char T;
	//Neutro
	char N;

	
	static Frame message = new Frame();
	
	static Registrazione r = new Registrazione();
	static Maps map = new Maps();
	static Utente u = new Utente();
	static POI poi = new POI();
	LogIn L = new LogIn();
	
	String p = "C:\\EmotionalMapsFile";
	String pathMap = "C:\\EmotionalMapsFile\\Mappa";
	static String pathStat ="C:\\EmotionalMapsFile\\Statistiche\\";
	Boolean show ;
	
	static int totFeel;
	
	public static void main(String[] args) {
	

	

	}
	
	/**
	 * Il Metodo  newPOI va a creare tramite l'utilizzo dei metodi newDir le directory EmotionalMapsFile e la subdirectory Mappa<br>
	 * l'iterazione for va a creare nella subdirectory Mappa i file denominati POI1, POI2, POI3
	 */
	public void newPOI() {

		r.newDir(p);
		r.newDir(pathMap);

		for(int i = 1; i<4;i++) {
			String pathfile = pathMap+"\\POI_"+i+".txt";
			File file = new File(pathfile);
			r.newFile(file);
		}
		

		
		
	}
	/**
	 * Il metodo va ad iscrivere nel file del POI (indicato dalla variabile z) i dati relativi agli spostamenti dell'utente 
	 * @param Maps è la matrice che viene generata nella Classe Main
	 * @param coorX longitudine
	 * @param coorY latitudine
	 * @param ID indica l'utente 
	 * @param feel indica l'emozione attuale dell'utente
	 * @param z indica a quale POI si fa riferimento(1,2,3)
	 */
	public void wrPOI(int[][]Maps, int coorX, int coorY, String ID, String feel, int z) {
		
		String pathfile = pathMap+"\\POI_"+z+".txt";	
		File file = new File(pathfile);
		//si va ad impostare nella variabile oggi la data attuale
		Date today = new Date();
		DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		String oggi = formatoData.format(today);
		//Si salva in un array il file indicato dal pathfile
		char[] strFile = L.rdFile(pathfile, file);
		
		try {
			BufferedWriter wr = new BufferedWriter(new FileWriter(file));
			String feelnow = feel;
			//si riscrive il file precedente
			wr.write(strFile);
			//si aggiunge i nuovi dati di spostamento e di emozione
			wr.write("Emozione : " + feelnow + " || "+"Data = " + oggi + " || " +"Coordinata x : "+coorX+ " || "+"Coordinata y : "+coorY+ " || "+ "ID : "+ ID );
			wr.newLine();
			wr.close();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
	
	
	/**
	 * il metodo va a mostrare tramite messaggi a schermo i contenuti dei file indicati da pathStatis
	 * @param pathStatis indica il path dei file da leggere
	 */
	public void show_stat(String pathStatis) {
		
			String replace_string = pathStatis;
		
		for( int i = 1; i<=3;i++) {
			// si specifica il POI di riferimento dando valore da 1 a 3
			pathStatis = pathStatis+i+".txt";
			File fileStatis = new File(pathStatis);
			//si legge  il file indicato da pathStatis e si carica su un array
			char[] s =  L.rdFile(pathStatis, fileStatis);
			//si imposta a vuoto il contenuto della stringa per il nuovo utilizzo
			String statistics_text = "" ;
			//si iscrive in una stringa tutto il contenuto dell'array 
			for(int k = 0; k<s.length; k++) {
				statistics_text = statistics_text + ""+s[k];
				
			}
			//si mostra a schermo un messaggio contenete le statistiche del file indicato dal pathStatis
			JOptionPane.showMessageDialog(message,"<html>Point of interest "+i+" </br>"+statistics_text);
			//si va ad reimpostare il valore originale di pathStatis
			pathStatis =replace_string;
		}
		
		
		
	}

	/**
	 * Il metodo va a contare le emozione associate ai movimenti di tutti gli utenti effettuati nel range del POI indicato da z<br>
	 * per poi calcolarne la percentuale e inserire i risultati nei file POI della directory Statistiche
	 * @param z indica il numero del POI
	 */
	public void countFeel(int z,String path ) {
		//Si imposta tramite la var z il path specifico del file nel quale sono presenti i movimenti/emozioni utente da leggere
		String pathStatis = path+z+".txt";	
		File fileStatis = new File(pathStatis);
		int f = 0 ;
		int a = 0 ;
		int s = 0 ;
		int t = 0 ;
		int n = 0 ;
	//si importa caratte per carattere il file in un array
	char[] strFile = L.rdFile(pathStatis, fileStatis);
	//si dichiara un array contenente per ogni emozione un carattere identificativo
	char[] feelcomp = {'F','A','S','T','N'};
	//si dichiara un array nel quale verrano salvati i valori letti per ogni emozione
	int [] feel = new int[5];
	//ad ogni nuovo ciclo si cambia il carattere dell'emozione associata 
	for(int i = 0 ; i<feel.length; i++) {
		//si fa la comparazione di tutti i caratteri del file con il carattere dell'emozione
		for(int k = 0; k< fileStatis.length(); k++) {
			
			if((strFile[k]==feelcomp[i])) {
				
					switch(i) {
					//f
					case 0 : 
						f++;
					break;
					
					//a
					case 1 : 
						a++;
					break;
					
					//s
					case 2 : 
						s++;
					break;
					
					//t
					case 3 : 
						t++;
					break;
					
					//n
					case 4 : 
						n++;
					break;
				
					}
					
				}
			
				
			}
			}
		
		
	//si carica l'array con i valori di ogni emozione
	feel[0]= f;
	feel[1]= a;
	feel[2]= s;
	feel[3]= t;
	feel[4]= n;
	
	//calcolo il totale delle emozioni rilevate
	totFeel = f+a+s+t+n;
	int length = 0;
	int position = 0;
	if(path=="C:\\EmotionalMapsFile\\Mappa\\POI_") {path ="C:\\EmotionalMapsFile\\Statistiche\\POI_";}
	
	if(path=="C:\\EmotionalMapsFile\\Query\\query_") {
		
		path ="C:\\EmotionalMapsFile\\Query\\Percentuali\\";
		r.newDir(path);
		path = path+"query_";
	}
	File file = new File(path+z+".txt");
	
	try {
		BufferedWriter wr = new BufferedWriter(new FileWriter(file));
		for(int i = 0 ; i<5; i++) {
		//calcolo la percentuale per ogni emozione
		int statistics_feel = (100*feel[i])/totFeel;
		//Iscrizione nel file della statistica associata alla posizione di i
		wr.write(feelcomp[i]+ " : "+ statistics_feel + "% || ");	
		
		
		}
		wr.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	}
	
	
	
/**
 * il metodo va comparare la data data in input e quelle presenti nel file indicato da pathStatis<br>
 * ritornando la posizione nel file dell'inizio della data che combacia
 * @param data
 * @param z iindica il POI
 * @param position indica la posizione di partenza dalla quale si fa la comparazione
 * @return @param position verrà utilizzato nel metodo {@link #wrDataQuery} come data di inizio o fine
 */
	public int showStatData (String data, int z, int position) {
		
	String pathStatis = "C:\\EmotionalMapsFile\\Mappa\\POI_"+z+".txt";	
	File fileStatis = new File(pathStatis);
		
	char[] strData = L.estrazione(data);
	
	char[] strFile = L.rdFile(pathStatis, fileStatis);
	int length = strData.length;
	
	
	//data la posizione 23 se il metodo arrayLoad ritorna true si imposta la posizione a 23
		if (L.arrayload(strData, strFile, length, position)==true) {
			for(int k = 0;(position == 23)&&(k<strFile.length);  k++)  {
			position = 23; 
			}
			
		}else { 
			int counter = 0; 
		
			int c=0;
			//imposto come riferimento di  ricerca nel file "=" che precede di una posizione l'inizio della data nella stringa di file
			String pos = "=";
			char[] new_position=  L.estrazione(pos);
			//ad ogni incremento di j (che varia in base ai dati)si scorre la data all'interno del strData ottenuto dall input
			for(int j = 0; j< strData.length;) {
			
			for(int i = counter ; i< strFile.length;i++)	 {
				//da un limite all'incremento di i e da un valore ad position
				if(i==strFile.length) {
					j=strData.length;
					position = strFile.length;
					break;
				}
				//x & y  assumono valore in base 
				char x = strData[j];
				//si incrementa la posizione di i tenendo conto della distanza del char "=" da il primo char della data e nel caso i suoi successivi
				char y = strFile[i+2+j];
				
				if(x!=y) {
					//si incrementa i  finche non è uguale ad "=" in modo da accelerare la ricerca
					do {
						i++;
						if(i==strFile.length)break;;
					}while((strFile[i] != new_position[0]));
					//si associa ad i a couter in quanto con break i si resetta
					counter = i;
					//si resettano j e c per necessità di algoritmo
					c=0;
					j=0;
					
					break;
				}
				if(x==y) {
					c++;
					j++;
					if((c==length)) {
						position = counter;
						break;
					} 
				break;	
				}
			}
			
			
			}
		

		}
		
		return position;
	}
	
	
	/**
	 * Dato un inizio e una fine il metodo va a iscrivere in un file apposito  quello che è contenuto nel file pathStatis e delimitato dalle due posizioni
	 * @param start_position
	 * @param end_position
	 * @param z
	 */
	public void wrDataQuery(int start_position, int end_position, int z ) {
		String pathStatis = "C:\\EmotionalMapsFile\\Mappa\\POI_"+z+".txt";	
		File fileStatis = new File(pathStatis);
		char[] strFile = L.rdFile(pathStatis, fileStatis);
		String show_stat = "";
		for(int po = start_position; start_position < end_position;start_position++) {
			show_stat = show_stat + strFile[start_position];
		}
			
	String path = "C:\\EmotionalMapsFile\\Query\\";
	r.newDir(path);
	File file = new File(path+"query_"+z+".txt");
	if(!file.exists()) {
		r.newFile( file);
	}
	try {
		BufferedWriter wr = new BufferedWriter(new FileWriter(file));
		wr.write(show_stat);
		wr.newLine();
		wr.close();

	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	}
}
