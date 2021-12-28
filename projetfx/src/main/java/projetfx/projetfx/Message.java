package projetfx.projetfx;

import java.util.Date;

public class Message {
	
	//public User emetteur;
	public String emetteur; 
	// j'ai changé ça pour que ça marche avec les classes de la bdd
	//public User recepteur;
	public String recepteur;
	public String contenu;
	public Date date;
	
	public Message(String emetteur1, String recepteur1, String contenu1, Date date1) {
		this.emetteur= emetteur1;
		this.recepteur= recepteur1;
		this.contenu= contenu1;
		this.date= date1;
	}

	// affiche le message 
	public void DisplayMessage() {
		// afficher le message à droite si le message est envoyé et à gauche s'il est reçu
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
