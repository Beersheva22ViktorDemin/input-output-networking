package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferCopy extends Copy {
	
	long bufferSize;

	public BufferCopy(String srcFilePath, String destFilePath, boolean overwrite, long bufferSize) {
		super(srcFilePath, destFilePath, overwrite);
		this.bufferSize = bufferSize;
	}

	@Override
	public long copy() {
		long result = -1;
		try (
				InputStream input = new FileInputStream(srcFilePath); 
				OutputStream output = new FileOutputStream(destFilePath);
			) {
//			result = input.re
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		return new DisplayResultBuffer(fileSize, copyTime, fileSize);
	}

}
