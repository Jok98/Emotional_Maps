package Soluzione;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
/**
 * 
 * @author Matteo Moi matricola n°737574<br>
 *
 *La classe è istituita per la creazione della GUI in seguito all'accesso, si gestisce :
 *<ul>
 *<li>l'inserimento delle coordinate dell'utente nei file appositi 
 *<li>la visualizzazione della mappa con la posizione dell'utente
 *<li>la visualizzazione delle statistiche specifiche per ogni Point of interest
 *<li>la visualizzazione delle statistiche generali su  % per ogni Point of interest
 *<li>il LogOut
 *<li>la disiscrizione dell'utente
 *</ul>
 */
public class GUI_LogIn extends JFrame {

	private JPanel contentPane;

	static Frame message = new Frame();
	
	static EmotionalMaps m = new EmotionalMaps();
	static Utente u = new Utente();
	static Registrazione r = new Registrazione();
	static LogIn L = new LogIn();
	static Maps map = new Maps();
	static POI poi = new POI();
	static GUI_Registrazione gui_reg = new GUI_Registrazione();
	static GUI_LogIn gui_log = new GUI_LogIn();
	
	static Boolean show = false;
	private JTextField tfData_Inizio;
	private JTextField tfData_Fine;
	
	static JTextField tfID;
	static JTextField tf0Password;
	
	/**
	 * Lancio dell'applicazione
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_LogIn frame = new GUI_LogIn();
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
	public GUI_LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creazione label x in indicazione alla longitudine
		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblX.setBounds(212, 11, 16, 19);
		contentPane.add(lblX);
		
		//Creazione label y in indicazione alla latitudine
		JLabel lblY = new JLabel("y");
		lblY.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblY.setBounds(314, 11, 16, 19);
		contentPane.add(lblY);
		
	
		//Creazione label lblMap nella quale verrà mostrata tramite richiamo del metodo showMap la mappa
		JLabel lblMap = new JLabel("");
		lblMap.setBackground(SystemColor.desktop);
		lblMap.setBounds(194, 131, 118, 109);
		contentPane.add(lblMap);
		
		//Creazione btnDisiscrizione 
		JButton btnDisiscrizione = new JButton("Disiscrizione");
		btnDisiscrizione.setBounds(355, 343, 114, 23);
		contentPane.add(btnDisiscrizione);
		//Gestione dell'evento click
		btnDisiscrizione.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * tramite ottenimento dell'ID si crea il path collegato al file dell'uente il quale poi verrà cancellato con file.delete()<br>
			 * si puliscono poi le textbox e si torna al frame principale 
			 */
			public void mouseClicked(MouseEvent e) {
				
				String ID = m.getID();
				String pathfile = m.pathUser+ID+".txt";
				File file = new File(pathfile);
			JOptionPane.showMessageDialog(message,pathfile);
			file.delete();
				JOptionPane.showMessageDialog(message ,"Ti sei disiscritto");
				u.cleanBox(m.tfID, m.tf0Password, m.tf0Password);
				m.FrameBack();
			}
		});
		
		//creazione btnLogout
		JButton btnLogout = new JButton("LogOut");
		btnLogout.setBounds(256, 343, 89, 23);
		contentPane.add(btnLogout);
		//Gestione dell'evento click
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * si puliscono poi le textbox e si torna al frame principale
			 */
			public void mouseClicked(MouseEvent e) {
				u.cleanBox(m.tfID, m.tf0Password, m.tf0Password);
				
				m.FrameBack();
			}
		});
		
		//Creazione btnPOI
		JButton btnPOI = new JButton("Stat. singole POI");
		btnPOI.setBounds(10, 343, 157, 23);
		contentPane.add(btnPOI);
		//Gestione dell'evento click
		btnPOI.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * Vengono mostrate le statistiche specifiche presenti nei file all'interno delle directory indicate dal path tramite l'evento show_stat 
			 */
			public void mouseClicked(MouseEvent e) {
				String pathStatis = "C:\\EmotionalMapsFile\\Mappa\\POI_";	
			poi.show_stat(pathStatis);
			}
		});
		
		//Creazione btnTotPoi
		JButton btnTotPoi = new JButton("Stat. TOT POI");
		btnTotPoi.setBounds(10, 309, 157, 23);
		contentPane.add(btnTotPoi);
		//Gestione dell'evento click
		btnTotPoi.addMouseListener(new MouseAdapter() {
			@Override
		/*
		 * Vengono mostrate le statistiche generali su % presenti nei file all'interno delle directory indicate dal path tramite l'evento show_stat 
		 */
			public void mouseClicked(MouseEvent e) {		
				String pathStatis = "C:\\EmotionalMapsFile\\Statistiche\\POI_";	
			poi.show_stat(pathStatis);
			}
		});
		
		//Creazione combobox X il quale contiene valori da 0 a 5 che vanno a indicare la longitudine
		JComboBox cbCooX = new JComboBox();
		cbCooX.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		cbCooX.setToolTipText("");
		cbCooX.setBounds(238, 11, 43, 22);
		contentPane.add(cbCooX);
		
		//Creazione combobox Y il quale contiene valori da 0 a 5 che vanno a indicare la latitudine
		JComboBox cbCooY = new JComboBox();
		cbCooY.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		cbCooY.setBounds(340, 11, 43, 22);
		contentPane.add(cbCooY);
		

		//Creazione btnShowMap
		JButton btnShowMap = new JButton("Mostra Mappa");
		btnShowMap.setBounds(10, 190, 157, 23);
		contentPane.add(btnShowMap);
		//Gestione dell'evento click
		btnShowMap.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * il metodo va a creare una mappa testuale;
			 *  tramite  le coordinate inserite dall'utente si va a inserire una X nella mappa per segnalare la posizione dell'utente
			 */
			public void mouseClicked(MouseEvent e) {
			int coorX = (int) cbCooX.getSelectedIndex();
			int coorY =(int) cbCooY.getSelectedIndex();	
			String t = null;

			lblMap.setText("<html>_| "+0+" |  "+1+" |"+"| "+2+" | "+3+" |  "+4+" |"+"| "+5+" | <BR>");
			for(int j = 0; j<= 5; j++) {
				
				if(j!=0) {
					lblMap.setText(lblMap.getText()+"|<BR>");
				}
				
				lblMap.setText(lblMap.getText()+""+j);
				for(int k = 0 ; k<=5; k++) {
					if((coorX==j)&&(coorY==k)) {
						t= " X ";
						lblMap.setText(lblMap.getText()+"|"+t);
					}else{
						t = "__";
						lblMap.setText(lblMap.getText()+"|"+t);}
			}
			}lblMap.setText(lblMap.getText()+"|</html>");
		}
				
			
		});
		
		//Creazione label per contestualizzare la combobox Feel
		JLabel lblNewLabel = new JLabel("Come ti senti ?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 64, 157, 28);
		contentPane.add(lblNewLabel);
		
		/*
		 * Creazione combobox Feel
		 * va a indicare l'attuale emozione dell'utente
		 */
		JComboBox cbFeel = new JComboBox();
		cbFeel.setModel(new DefaultComboBoxModel(new String[] {"", "F", "A", "S", "T", "N"}));
		cbFeel.setToolTipText("");
		cbFeel.setBounds(212, 69, 43, 22);
		contentPane.add(cbFeel);
		
		//Creazione btnInsCoor
		JButton btnInsCoor = new JButton("Inserisci Coordinate");
		btnInsCoor.setBounds(10, 11, 157, 23);
		contentPane.add(btnInsCoor);
		//Gestione dell'evento click
		btnInsCoor.addMouseListener(new MouseAdapter() {
			@Override
			/*
			 * si crea una mappa (matrice) che verrà utilizzata dai metodi wrPOI e compareCoor
			 * si va ad identificare tramite pathfile e file il file collegato all'utente
			 * 
			 */
			public void mouseClicked(MouseEvent e) {
				int[] [] Maps = new int[6][6];
				String ID = m.getID();
				String pathfile = m.pathUser+ID+".txt";
				File file = new File(pathfile);
				
				do {
				//la matrice viene riempita con valori da 0 a 35
				Maps=map.setMap(Maps);
				//si gestisce l'eccezione nel caso in cui non venga selezionata un emozione dall'utente
				if(cbFeel.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(message,"Seleziona un emozione");
					break;
				}
				show = true;
				
				//dichiarazione variabili per coordinate x & y
				int coorX = (int) cbCooX.getSelectedIndex();
				int coorY =(int) cbCooY.getSelectedIndex();
				//si salva in variabile il valore Feel selezionato dall'utente
				String feel = (String)cbFeel.getSelectedItem();
			//scrive le coordinate nel file utente
			map.setPosition(pathfile, file, coorX, coorY );
			//il metodo richiamato ritorna il valore int(1,2,3) del POI a cui si fa riferimento se le coordinate sono nel range
			int z = map.compareCoor(Maps, coorX, coorY,show);
			//scrive nel file poi(dir mappa) indicato da z i dati utente e le sue coordinate
			poi.wrPOI(Maps, coorX, coorY,ID, feel, z);
			//calcola e scrive nel file poi(dir statistiche) indicato da z le statistiche generali in %
			String path ="C:\\EmotionalMapsFile\\Mappa\\POI_";
			poi.countFeel(z,path);
			break;
				}while(true);		
			
			}
		});


		//Creazione label per legenda Emozioni utente
		JLabel lblNewLabel_1 = new JLabel("<html>\r\nF = Felice ;  A = Arrabbiato:<BR>\r\nS = Sorpreso ;  T = Triste ;  N = Neutro</html>\r\n");
		lblNewLabel_1.setBounds(265, 64, 214, 39);
		contentPane.add(lblNewLabel_1);
		
		//Creazione label per legenda range Point of interest
		JLabel lblNewLabel_2 = new JLabel("<html>Poi1 : (0;0) (0;1) (1;0) (1;1)<br>\r\nPoi2 : (0;4) (0;5) (1;4) (1;5)<br>\r\nPoi3 : (4;2) (4;3) (5;2) (5;3)</html>");
		lblNewLabel_2.setBounds(314, 131, 157, 51);
		contentPane.add(lblNewLabel_2);
		
		
		
		//creazione tfData_Inizio
		tfData_Inizio = new JTextField();
		tfData_Inizio.setHorizontalAlignment(SwingConstants.CENTER);
		tfData_Inizio.setText("gg/mm/aa");
		tfData_Inizio.setBounds(212, 265, 86, 20);
		contentPane.add(tfData_Inizio);
		tfData_Inizio.setColumns(10);
		
		//creazione tfData_Fine
		tfData_Fine = new JTextField();
		tfData_Fine.setHorizontalAlignment(SwingConstants.CENTER);
		tfData_Fine.setText("gg/mm/aa");
		tfData_Fine.setBounds(340, 265, 86, 20);
		contentPane.add(tfData_Fine);
		tfData_Fine.setColumns(10);
		
		//creazione btnStatData
		JButton btnStatData = new JButton("Statistiche su data");
		btnStatData.setBounds(10, 264, 157, 23);
		contentPane.add(btnStatData);
		
		JLabel lblNewLabel_3 = new JLabel("Da :");
		lblNewLabel_3.setBounds(178, 268, 24, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblA = new JLabel("A :");
		lblA.setBounds(314, 268, 24, 14);
		contentPane.add(lblA);
		btnStatData.addMouseListener(new MouseAdapter() {
			@Override
			//L'evento gestisce delle query che vanno a prelevare i dati rientranti nelle date indicate dall'utente
			public void mouseClicked(MouseEvent e) {
				//Si imposta la data iniziale e finale 
				String data1 = tfData_Inizio.getText();
				String data2 = tfData_Fine.getText();
				String path ="C:\\EmotionalMapsFile\\Query\\query_";
			
				for(int z=1;z<=3;z++) {
				//la start_position va a indicare nel file la posizione da dove inizia la data
				int start_position = 23;
				int end_position ;
				//start_position prende il valore restituito dal metodo che è la posizione nel file dalla quale si vuole mostrare il contenuto
				start_position = poi.showStatData(data1,z,start_position);
				//end_position prende il valore restituito dal metodo che è la posizione nel file dalla quale si smette di mostrare il file
				end_position = poi.showStatData(data2,z,start_position);
				//va a iscrivere nei file appositi i risultati delle query start_position e end_position
				poi.wrDataQuery(start_position-21, end_position-22, z);
				//iscrive nei file appositi i risultati delle query su base % 
				poi.countFeel(z, path);
				//le seguenti stringhe vanno a prelevare i file indicati dal path e mostrano a schermo le statistiche in % delle query
				String pathQuery = "C:\\EmotionalMapsFile\\Query\\Percentuali\\";
				File fileQuery = new File(pathQuery+"query_"+z+".txt");
				char[] char_Query = L.rdFile(pathQuery, fileQuery);
				String str_Query = "";
				
				for(int i = 0 ; i< fileQuery.length();i++) {
					str_Query = str_Query + char_Query[i];
				}
				JOptionPane.showMessageDialog(message,str_Query);
				}
							
			}
		});
		
	}
}
