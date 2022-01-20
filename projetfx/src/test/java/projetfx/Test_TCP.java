package projetfx;

import org.junit.Test;
import java.sql.SQLException;
import projetfx.projetfx.*;

public class Test_TCP {
	
	@Test
    public void testTCP() throws ClassNotFoundException, SQLException {
		TCP_serveur.receive();
		TCP_client.send("hey", "hey", "hey");
	}
}
