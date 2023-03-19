package telran.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TcpClientHandlerTest {
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 4001;
	static Handler handler;
	static Logger logger;
	
	@BeforeEach
	void setUp() throws UnknownHostException, IOException {
		Socket socket = new Socket(HOSTNAME, PORT);
		PrintStream writer = new PrintStream(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		handler = new TcpClientHandler(socket, writer, reader);
		logger = new Logger(handler, "test-logger");
	}
	
	@Test
	@Timeout(50)
	void testSend() {
		logger.info("message");
	}
	
	@AfterAll
	static void setDown() {
		handler.close();
	}

}
