package projetfx.projetfx;

import java.sql.Timestamp;

public class Message {
	
	//public User emetteur;
	public String emetteur; 
	// j'ai changé ça pour que ça marche avec les classes de la bdd
	//public User recepteur;
	public String recepteur;
	public String contenu;
	@SuppressWarnings("exports")
	public Timestamp time;
	
	@SuppressWarnings("exports")
	public Message(String emetteur1, String recepteur1, String contenu1, Timestamp time2) {
		this.emetteur= emetteur1;
		this.recepteur= recepteur1;
		this.contenu= contenu1;
		this.time= time2;
	}

	

	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

}
