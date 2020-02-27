package Soluzione;
import javax.swing.JTextField;

/**
 * 
 * @author Matteo Moi matricola n°737574
 *
 */
public class Utente {

	static GUI_Registrazione gui_reg = new GUI_Registrazione();
	
	String Nome;
	String Cognome;
	String ID;
	String Password;
	
	/**
	 * 	Il metodo ritorna una stringa contenente il testo presente nella tfNome della classe EmotionalMaps
	 * @return Nome
	 */
	public static String getNome() {

		String Nome = gui_reg.tfNome.getText();
		Nome = Nome.toLowerCase();
		return Nome;
	}
	
	/**
	 * Il metodo ritorna una stringa contenente il testo presente nella tfCognome della classe EmotionalMaps
	 * @return Cognome
	 */
	public static String getCognome() {

		String Cognome = gui_reg.tfCognome.getText();
		Cognome = Cognome.toLowerCase();
		return Cognome;
	}
	
	/**
	 * Dato in input il Nome e il Cognome (presenti nelle relative textbox nella classe GUI_Registrazione)<br>
	 * il metodo restituisce l'ID dell'utente dato dalla combinazione Nome.Cognome
	 * @param Nome
	 * @param Cognome
	 * @return ID
	 */
	public String setID(String Nome, String Cognome) {
		String ID = Nome + "." + Cognome;
		ID.toLowerCase();
		return ID;
	}
	

	/**
	 * Il metodo ritorna una stringa contenente il testo presente nella tfPassword della classe EmotionalMaps
	 * @return Password
	 */
	public static String getPassword() {

		String Password = gui_reg.tfPassword.getText();
		Password = Password.toLowerCase();
		return Password;
	}

	/**
	 * @param ID
	 * @return lenght
	 */
	public int getIDLength(String ID) {
		
		int length = ID.length();
		
		return length;
	}
	
	/**
	 * Il metodo calcola la lunghezza della password
	 * @param Password
	 * @return lenght
	 */
	public int getPassLength(String Password) {
		
		int length = Password.length();

		return length;
	}
	
	/**
	 * @param arrgs
	 */
	public static void main (String[] arrgs) {
	

	}
	/**
	 * Il metodo cancella il testo delle textbox date in input
	 * @param tf1
	 * @param tf2
	 * @param tf3
	 */
	public void cleanBox(JTextField tf1,JTextField tf2,JTextField tf3) {
		tf1.setText("");
		tf2.setText("");
		tf3.setText(null);
	}
	
	
}
