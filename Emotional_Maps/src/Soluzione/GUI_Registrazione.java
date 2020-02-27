package Soluzione;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Matteo Moi matricola n°737574
 *
 */

public class GUI_Registrazione extends JFrame {

	private JPanel contentPane;
	JTextField tfNome;
	JTextField tfCognome;
	JTextField tfPassword;
	
	static EmotionalMaps m = new EmotionalMaps();
	static Utente u = new Utente();
	static Registrazione r = new Registrazione();
	static LogIn L = new LogIn();
	static Maps map = new Maps();
	static POI poi = new POI();
	static GUI_Registrazione gui_reg = new GUI_Registrazione();
	static GUI_LogIn gui_log = new GUI_LogIn();
	
	static String p = "C:\\EmotionalMapsFile";
	static String path = "C:\\EmotionalMapsFile\\Utenti\\";
	static Boolean show = false;

	
	static Frame message = new Frame();
	static GUI_Registrazione frame = new GUI_Registrazione();
	
	/**
	 * Lancio dell'applicazione
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creazione del frame</br>
	 * Settaggio impostazioni/attributi del frame</br>
	 */
	public GUI_Registrazione() {
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setTitle("Registrazione");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creazione lblNome
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 63, 14);
		contentPane.add(lblNome);
		
		//Creazione lblCognome
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(10, 36, 63, 14);
		contentPane.add(lblCognome);
		
		//Creazione lblPassword
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 61, 63, 14);
		contentPane.add(lblPassword);
		
		//Creazione textfiel tfNome
		tfNome = new JTextField();
		tfNome.setBounds(83, 8, 86, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		//GESTIONE EVENTO RILASCIO DI UNA KEY
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			//L'evento va a settare in automatico tutto il testo inserito dall'utente come minuscolo
			public void keyReleased(KeyEvent e) {
				int pos = tfNome.getCaretPosition();
		        tfNome.setText(tfNome.getText().toLowerCase());
		        tfNome.setCaretPosition(pos);
			}
		});
		
		
		//Creazione textfiel tfNome
		tfCognome = new JTextField();
		tfCognome.setBounds(83, 33, 86, 20);
		contentPane.add(tfCognome);
		tfCognome.setColumns(10);
		//GESTIONE EVENTO RILASCIO DI UNA KEY
		tfCognome.addKeyListener(new KeyAdapter() {
			@Override
			//L'evento va a settare in automatico tutto il testo inserito dall'utente come minuscolo
			public void keyReleased(KeyEvent e) {
				int pos = tfCognome.getCaretPosition();
				tfCognome.setText(tfCognome.getText().toLowerCase());
				tfCognome.setCaretPosition(pos);
			}
		});
		
		
		//Creazione btnConferma 
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setBounds(175, 227, 105, 23);
		contentPane.add(btnConferma);
		//Gestione evento click
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * si crea l'eventuale directory "C:\\EmotionalMapsFile\\Utenti\\"
			 * tramite i dati inseriti nelle apposite textfield si va a creare il file utente con salvati i dati ad esso relativi
			 */
			public void mouseClicked(MouseEvent e) {
				
				u.Nome = tfNome.getText();
				u.Cognome = tfCognome.getText();
				u.ID = u.setID(u.Nome, u.Cognome  );
				u.Password = tfPassword.getText();
				String pathfile = path+u.ID+".txt";
				File file = new File(pathfile);
				file = new File(pathfile);	
				//richiamo i metodi per creazione, scrittura e salvataggio file
				r.newDir(p);
				r.newDir(path);
				r.wrFile(pathfile, file, u, r);
			
			}
		});
		
		
		//Creazione textfiel tfPassowrd
		tfPassword = new JTextField();
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int pos = tfPassword.getCaretPosition();
				tfPassword.setText(tfPassword.getText().toLowerCase());
				tfPassword.setCaretPosition(pos);
			}
		});
		tfPassword.setBounds(83, 58, 86, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		//Creazione btnBack
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(335, 227, 89, 23);
		contentPane.add(btnBack);
		//gestione evento click
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * si cancella il contenuto delle textfield
			 * si nasconde il frame GUI_Registrazione e si mostra il main EmotionalMaps
			 */
			public void mouseClicked(MouseEvent e) {
			u.cleanBox(tfNome, tfCognome, tfPassword);
			m.FrameBack();
			
			}
		});
		
	}
}
