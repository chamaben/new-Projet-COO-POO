package projetfx;

import projetfx.projetfx.*;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.Test;

class Test_UDP {

	@Test
	void testUDP() throws ClassNotFoundException, IOException, SQLException {
		UDP_serveur udp = new UDP_serveur();
		udp.run();
		UDP_client.connexion("chama", "1", "100.00.00.00");
	}

}
