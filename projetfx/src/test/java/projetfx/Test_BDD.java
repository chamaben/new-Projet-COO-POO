package projetfx;

import static org.junit.jupiter.api.Assertions.*;

import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import projetfx.projetfx.*;

class Test_BDD {

	@Test
	void testBDD() throws ClassNotFoundException, SQLException, SocketException {
		User user = new User("tt","yy","xx");
		User.CreateUser(user.login, user.password, user.pseudo, user.etat);
		assertEquals(true,User.UserExist("tt"));
		
		assertEquals("xx",user.getPseudo());
		assertEquals(1,user.getEtat());
		assertEquals(true,User.PseudoValide("tt","xx"));
		
		//User.modifyPseudo(user,"dd");
		//(true,User.PseudoValide("tt","dd"));
		//assertEquals("dd",user.getPseudo());
		
		assertEquals(true,User.VerifPassword("tt", "yy"));
		
		DbConnect.Connexion();
		int c = DbConnect.statement.executeUpdate("DELETE FROM `tp_servlet_002`.`user` WHERE (`login` = 'tt')");
		assertEquals(1,c);//nb de lignes modifiées
		DbConnect.FinConnexion();
	}

}
