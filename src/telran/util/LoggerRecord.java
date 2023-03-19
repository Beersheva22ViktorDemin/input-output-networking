package telran.util;

import java.io.*;
import java.time.Instant;
import java.util.Base64;

public class LoggerRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	public final Instant timestamp;
	public final String zoneId;
	public final Level level;
	public final String loggerName;
	public final String message;

	public LoggerRecord(Instant timestamp, String zoneId, Level level, String loggerName, String message) {
		this.timestamp = timestamp;
		this.zoneId = zoneId;
		this.level = level;
		this.loggerName = loggerName;
		this.message = message;
	}
	
	public String serializeToString() throws IOException {
		String result = null;
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    try(ObjectOutputStream os = new ObjectOutputStream(bos)) {
		    os.writeObject(this);
		    result = Base64.getEncoder().encodeToString(bos.toByteArray());
	    }
	    
	    return result;
	}
	
	public static LoggerRecord unserializeFromString(String string) throws IOException, ClassNotFoundException {
		LoggerRecord result = null;
		byte[] data = Base64.getDecoder().decode(string);
		
	    ByteArrayInputStream bis = new ByteArrayInputStream(data);
	    try(ObjectInputStream oInputStream = new ObjectInputStream(bis)) {
	    	result = (LoggerRecord) oInputStream.readObject();
	    }

	    return result;
	}
}