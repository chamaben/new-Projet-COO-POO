package projetfx.projetfx;

import java.util.Date;

public class Message {
	
	public User emetteur;
	public User recepteur;
	public String contenu;
	public Date date;
	
	public Message(User emetteur1, User recepteur1, String contenu1, Date date1) {
		this.emetteur= emetteur1;
		this.recepteur= recepteur1;
		this.contenu= contenu1;
		this.date= date1;
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
