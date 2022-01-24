package projetfx;

import static org.junit.jupiter.api.Assertions.*;

import java.net.SocketException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import projetfx.projetfx.*;

class Test_BDD {

	@Test
	void testBDD() throws ClassNotFoundException, SQLException, SocketException {
		User user = new User("aa","bb","cc");
		User.CreateUser(user.login, user.password, user.pseudo, user.etat);
		assertEquals(true,User.UserExist("user", "p"));
		
		assertEquals("cc",user.getPseudo());
		assertEquals(1,user.getEtat());
		assertEquals(true,User.PseudoValide("aa","cc"));
		
		user.modifyPseudo(user,"dd");
		assertEquals(true,User.PseudoValide("aa","dd"));
		assertEquals("dd",user.getPseudo());
		
		user.modifyPseudo(user,"cc");
		assertEquals("cc",user.getPseudo());
		
		assertEquals(true,User.VerifPassword("user", "bb"));
	}

}
