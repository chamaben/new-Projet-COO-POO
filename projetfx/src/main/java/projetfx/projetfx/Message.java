package projetfx.projetfx;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Message {
	
	//public User emetteur;
	public String emetteur; 
	// j'ai changé ça pour que ça marche avec les classes de la bdd
	//public User recepteur;
	public String recepteur;
	public String contenu;
	public Timestamp date;
	
	public Message(String emetteur1, String recepteur1, String contenu1, Timestamp date2) {
		this.emetteur= emetteur1;
		this.recepteur= recepteur1;
		this.contenu= contenu1;
		this.date= date2;
	}
}
