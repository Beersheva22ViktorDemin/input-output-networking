package telran.io;

public class DisplayResultBuffer extends DisplayResult {
	
	private long bufferSize;

	public DisplayResultBuffer(long fileSize, long copyTime, long bufferSize) {
		super(fileSize, copyTime);
		this.bufferSize = bufferSize;
	}
	
	public String toString() {
		return super.toString() + String.format(
				" bufferSize: %s",
				bufferSize
		);
	}
}
