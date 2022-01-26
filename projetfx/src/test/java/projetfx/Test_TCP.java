package projetfx;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import projetfx.projetfx.*;

public class Test_TCP {
	
	@Test
    public void testTCP() throws ClassNotFoundException, SQLException {
		try {
			TCP_serveur.receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TCP_client.send("hey", "hey", "hey");
	}
}
