package telran.net.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Level;

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
}
