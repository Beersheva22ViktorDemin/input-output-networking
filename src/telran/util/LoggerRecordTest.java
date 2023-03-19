package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoggerRecordTest {
	LoggerRecord record;
	
	@BeforeEach
	void setUp() {
		record = new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(), Level.INFO, "name", "message");
	}
	
	@Test
	void test() throws IOException, ClassNotFoundException {
		String string = record.serializeToString();
		LoggerRecord newObject = record.unserializeFromString(string);
		assertEquals(record.timestamp, newObject.timestamp);
		assertEquals(record.zoneId, newObject.zoneId);
		assertEquals(record.level, newObject.level);
		assertEquals(record.loggerName, newObject.loggerName);
		assertEquals(record.message, newObject.message);
	}
}
