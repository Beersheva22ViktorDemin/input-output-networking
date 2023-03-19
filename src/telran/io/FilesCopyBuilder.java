package telran.io;

public class FilesCopyBuilder {
	public FilesCopy build(String type, String[] args) {
		FilesCopy result = null;
		switch (type) {
		case "BufferCopy": {
//			result = new BufferCopy(type, type, false, 0);
			result = null;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		return result;
	}
}
