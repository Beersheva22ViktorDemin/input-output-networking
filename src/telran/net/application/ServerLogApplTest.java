package telran.net.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import telran.util.Level;
import telran.util.LoggerRecord;

@TestMethodOrder(OrderAnnotation.class)
public class ServerLogApplTest {
	ServerLogAppl obj;
	
	@BeforeEach
	void setUp() {
		obj = new ServerLogAppl();
	}
	
	@Test
	@Order(1)
	void getCounterTest() {
		assertEquals("Wrong level some", obj.getCounter("some"));
		Arrays.stream(Level.values()).forEach(level -> {
			assertEquals("0", obj.getCounter(level.toString()));
		});
	}
	
	@Test
	@Order(2)
	void saveLog() throws IOException {
		assertEquals("Wrong log format", obj.saveLog("some"));
		LoggerRecord record = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(), Level.INFO, "name", "message");
		assertEquals("OK", obj.saveLog(record.serializeToString()));
		
		assertEquals("1", obj.getCounter(record.level.toString()));
	}
	
	@Test
	@Order(3)
	void getResponseTest() throws IOException {
		assertEquals("Wrong Request", obj.getResponse("some"));
		assertEquals("Wrong type some", obj.getResponse("some#debug"));
		assertEquals("0", obj.getResponse("counter#debug"));
		assertEquals("Wrong log format", obj.getResponse("log#some"));
		
		LoggerRecord record = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(), Level.DEBUG, "name", "message");
		assertEquals("OK", obj.getResponse("log#" + record.serializeToString()));
	}
}
