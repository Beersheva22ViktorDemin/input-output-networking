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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TcpClientHandlerTest {
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 4001;
	static PrintStream writer;
	static BufferedReader reader;
	static Handler handler;
	static Logger logger;
	
	@BeforeEach
	void setUp() throws UnknownHostException, IOException {
		Socket socket = new Socket(HOSTNAME, PORT);
		writer = new PrintStream(socket.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		handler = new TcpClientHandler(socket, writer, reader);
		logger = new Logger(handler, "test-logger");
	}
	
	@Test
	void testSend() throws IOException {
		logger.info("message");
	}
	
	@Test
	void testCounter() {
		String res = getCounter("info");
		System.out.println(res);
	}
	
	public String getCounter(String level) {
		String respone = "";
		try {
			writer.println("counter#" + level);
			respone = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respone;
	}
	
	@AfterAll
	static void setDown() {
		handler.close();
	}

}
