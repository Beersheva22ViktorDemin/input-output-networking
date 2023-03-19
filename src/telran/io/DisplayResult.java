package telran.io;

public class DisplayResult {
	
	private long fileSize;
	private long copyTime;
	
	public DisplayResult(long fileSize, long copyTime) {
		super();
		this.fileSize = fileSize;
		this.copyTime = copyTime;
	}
	
	@Override
	public String toString() {
		return String.format(
				"fileSize: %s bytes, copyTime: %s ns",
				fileSize,
				copyTime
		);
	}

}
