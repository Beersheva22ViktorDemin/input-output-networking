package telran.io;

import java.nio.file.Files;

public class FilesCopy extends Copy {

	public FilesCopy(String srcFilePath, String destFilePath, boolean overwrite) {
		super(srcFilePath, destFilePath, overwrite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long copy() {
//		Files.copy(srcFilePath, destFilePath);
		return 0;
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		return new DisplayResult(fileSize, copyTime);
	}

}
