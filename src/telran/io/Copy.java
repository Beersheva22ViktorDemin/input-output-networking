package telran.io;

public abstract class Copy {
	String srcFilePath;
	String destFilePath;
	boolean overwrite;
	
	public Copy(String srcFilePath, String destFilePath, boolean overwrite) {
		super();
		this.srcFilePath = srcFilePath;
		this.destFilePath = destFilePath;
		this.overwrite = overwrite;
	}

	public abstract long copy();
	
	abstract DisplayResult getDisplayResult(long copyTime, long fileSize);
	
	public void copyRun() {
		long startTime = System.nanoTime();
		long fileSize = copy();
		long endTime = System.nanoTime();		
		String result = getDisplayResult(endTime - startTime, fileSize).toString();
		System.out.println(result);
	}
}
