package telran.net.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Level;
import telran.util.LoggerRecord;

public class ServerLogApplTest {
	ServerLogAppl obj;
	
	@BeforeEach
	void setUp() {
		obj = new ServerLogAppl();
	}
	
	@Test
	void getCounterTest() {
		assertEquals("Wrong level some", obj.getCounter("some"));
		Arrays.stream(Level.values()).forEach(level -> {
			assertEquals("0", obj.getCounter(level.toString()));
		});
	}
	
	@Test
	void saveLog() throws IOException {
		assertEquals("Wrong log format", obj.saveLog("some"));
		LoggerRecord record = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(), Level.INFO, "name", "message");
		assertEquals("OK", obj.saveLog(record.serializeToString()));
		
		assertEquals("1", obj.getCounter(record.level.toString()));
	}
}
