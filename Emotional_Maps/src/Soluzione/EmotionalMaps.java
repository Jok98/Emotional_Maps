package Soluzione;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Soluzione.GUI_LogIn;
import Soluzione.LogIn;
import Soluzione.Maps;
import Soluzione.POI;
import Soluzione.Registrazione;
import Soluzione.Utente;

/**
 * @author Matteo Moi matricola n°737574
 * 
 */

public class EmotionalMaps {
/*
 Vengono dichiarate le variabili globali associate a tutte le classi, esse verranno utilizzate per richiamare i metodi associati
  
 */
	static EmotionalMaps m = new EmotionalMaps();
	static Utente u = new Utente();
	static Registrazione r = new Registrazione();
	static LogIn L = new LogIn();
	static Maps map = new Maps();
	static POI poi = new POI();
	static GUI_Registrazione gui_reg = new GUI_Registrazione();
	static GUI_LogIn gui_log = new GUI_LogIn();
/*
 	Dichiarazione variabili per la gestione del frame e dei messaggi a schermo
 */
	static private JFrame frame;
	static Frame message = new Frame();
	
/*
  Dichiarazione variabili con assegnazione di path per la creazione e gestione delle directory e dei  file
 */
	static String p = "C:\\EmotionalMapsFile";
	static String pathUser = "C:\\EmotionalMapsFile\\Utenti\\";
	static String pathStat ="C:\\EmotionalMapsFile\\Statistiche\\";
	
	static Boolean show = false;
/*
  Dichiarazione a livello globale dei TextField ID e Password per permetterne l'utilizzo a tutte le classi del package
 */
	static JTextField tfID;
	static JTextField tf0Password;
	
	
/**
 * @param args
 * Viene richiamato il metodo {@link Maps#setMap(int[][])}</br>
 * Viene richiamato il metodo {@link POI#newPOI()}</br>
 * Viene richiamato il metodo {@link Registrazione#newDir(String)}</br>
 */
	public static void main(String[] args) {

		int[] [] Maps = new int[6][6];
		map.setMap(Maps);	
		poi.newPOI();
		r.newDir(pathStat);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmotionalMaps window = new EmotionalMaps();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}


	/**
	 * Creazione applicazione
	 */
	public EmotionalMaps() {
		initialize();
	}

	private void initialize() {
		
		/* Inizzializzazione del contenuto del frame</br>
		 *Settaggio impostazioni/attributi del frame</br>
		 */
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(169, 169, 169));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		// Creazione button btnRegistrazione
		JButton btnRegistrazione = new JButton("Registrazione");
		btnRegistrazione.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnRegistrazione.setBounds(10, 67, 135, 92);
		frame.getContentPane().add(btnRegistrazione);
		//Gestione dell' evento click btnRegistrazione mouseClicked
				btnRegistrazione.addMouseListener(new MouseAdapter() {
					@Override
					/*
					 Viene richiamato il metodo setVisible() per visualizzare il frame Gui_Registrazione e per nascondere il frame attuale(EmotionalMaps)
					 Viene richiamato il metodo cleanBox() per cancellare i contenuti delle TextBox
					 */
					public void mouseClicked(MouseEvent e) {
			
						gui_reg.setVisible(true);
						frame.setVisible(false);
						u.cleanBox(tfID, tf0Password, tf0Password);
						
					}
				});

		// Creazione button btnLogin
		JButton btnLogin = new JButton("LogIn");
		btnLogin.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLogin.setBounds(155, 67, 126, 92);
		frame.getContentPane().add(btnLogin);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			/* 
			 Gestione dell' evento click su btnLogin
			 Tramite l'evento viene gestita la comparazione degli array di char ID e Password inseriti dall'utente  (costruiti tramite metodo estrazione(String) della classe LogIn
			 con i file utente presenti nella directory C:\\EmotionalMapsFile\\Utenti\\
			 */
			public void mouseClicked(MouseEvent e) {

				String ID = tfID.getText();
				String Password = tf0Password.getText();
				String pathfile = pathUser+ID+".txt";
				File file = new File(pathfile);
				int IDlength = ID.length();
				int Passlength = Password.length();
				
				/*inizzializzazione degli array per il metodo arrayLoad
				 * estraggo carattere per carattere l'ID e carico  su array di char
				*/
				char[] strID=  L.estrazione(ID);
				
				//estraggo carattere per carattere la password e carico  su array di char
				char[] strPass = L.estrazione(Password);
				
				//estraggo carattere per carattere l'intero file utente e carico  su array di char
				char[] strFile = L.rdFile(pathfile, file);
				
				//indico la posizione dell'ID nel file 
				int ID_position = 5;
				
				//indico la posizione della password nel file 
				int Pass_position = 5 + IDlength + 15;
				
				/*richiamo metodo arrayLoad per comparare l'ID dato in input con ID presente nel file
				*se viene restituito true si passa alla comparazione di password, in caso contrario viene mostrato un error message
				*/
				if(L.arrayload(strID, strFile, IDlength, ID_position)==true) {
					
					/*richiamo metodo arrayLoad per comparare la password data in input con Password presente nel file
					 * se viene restituito true viene mostrato il frame GUI_LogIn e nascosto il frame attuale (EmotionalMaps), in caso contrario viene mostrato un error message
					 */
					if(L.arrayload(strPass, strFile, Passlength,Pass_position)==true) {
						JOptionPane.showMessageDialog(message,"Accesso effettuato");
						gui_log.setVisible(true);
						frame.setVisible(false);
						
					}else JOptionPane.showMessageDialog(message,"Accesso negato : credenziali errate o inesistenti!" ); {
						
					}
					
				}else JOptionPane.showMessageDialog(message,"Accesso negato : credenziali errate o inesistenti!" );{
					
				}
				
			}
		});
		
		/*
		 * Creazione button btnExit
		 * 
		 */
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnExit.setBounds(289, 67, 135, 92);
		frame.getContentPane().add(btnExit);
		
		//Gestione dell' evento click su btnExit che chiude l'applicazione
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//Creazione label ID
		JLabel lblID = new JLabel("ID : ");
		lblID.setBounds(192, 196, 30, 14);
		frame.getContentPane().add(lblID);
		
		//Creazione label Password
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(143, 221, 79, 14);
		frame.getContentPane().add(lblPassword);
		
		//Creazione textfield tfID
		tfID = new JTextField();
		tfID.setBounds(221, 193, 86, 20);
		frame.getContentPane().add(tfID);
		tfID.setColumns(10);
		
		//Creazione textfield tfPassword
		tf0Password = new JTextField();
		tf0Password.setBounds(221, 218, 86, 20);
		frame.getContentPane().add(tf0Password);
		tf0Password.setColumns(10);
	}
	
	/**
	 * Il metodo restituisce il testo all'interno dell' {@link #tfID}
	 * @return ID viene prelevato il testo all'interno della tfID
	 */
	public String getID() {

		String ID = tfID.getText();
		
		return ID;
	}
	
	/**
	 * Il metodo FrameBack chiude i frame secondari Gui_Registrazione e Gui_LogIn e imposta il frame EmotionalMap come visibile
	 */
	public void FrameBack ()  {

		gui_reg.dispose();
		gui_log.dispose();
		frame.setVisible(true);
		
	}
	
}
