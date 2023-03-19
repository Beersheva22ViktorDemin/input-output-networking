package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TransferCopy extends Copy {

	public TransferCopy(String srcFilePath, String destFilePath, boolean overwrite) {
		super(srcFilePath, destFilePath, overwrite);
	}

	@Override
	public long copy() {
		long result = -1;
		try (
				InputStream input = new FileInputStream(srcFilePath); 
				OutputStream output = new FileOutputStream(destFilePath);
			) {
			result = input.transferTo(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		return new DisplayResult(fileSize, copyTime);
	}

}
