package telran.util;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class TcpClientHandlerTest {
	static Handler handler;
	static Logger logger;
	
	@BeforeEach
	static void setUp() throws FileNotFoundException {
//		handler = new TcpClientHandler();
		logger = new Logger(handler, "test-logger");
	}
	
	@AfterAll
	static void setDown() {
		handler.close();
	}

}
