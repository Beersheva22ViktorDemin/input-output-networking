package telran.io.test;
import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
String fileName = "myFile";
String directoryName = "myDirectory1/myDirectory2";
	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	@Disabled
	void testFile() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1.getAbsolutePath());
		
		
	}
	@Test
	@Disabled
	void printDirectoryFileTest() throws IOException {
		//TODO testing printDirectoryFile
		printDirectoryFile(".", 2);
	}
	void printDirectoryFile(String path, int maxLevel) throws IOException {
		//TODO based on class File
		//path -directory path
		//maxLevel - maximal level of printing, if maxLevel < 1, all levels should be printed
		//output format
		//  <directory name (no points, no full absolute path)
		//     <node name> - dir | file
		//          <node_name> .....
		//     <node name> -
		//          <node name> - dir | file
		//                <node_name> .....
		//     <node name> -
	    File dir = new File(path);
	    if (!dir.isDirectory()) {
	        System.out.println(path + " is not a directory");
	        return;
	    }
	    printDirectoryContents(dir, maxLevel, 0);
	}

	private void printDirectoryContents(File dir, int maxLevel, int level) throws IOException {
	    String prefix = " ".repeat(level * 4);
	    System.out.println(prefix + dir.getPath() + " - dir");
	    File[] files = dir.listFiles();
	    if (files != null) {
	        for (File file : files) {
	            if (file.isDirectory()) {
	                if (maxLevel < 1 || level < maxLevel) {
	                    printDirectoryContents(file, maxLevel, level + 1);
	                } else {
	                    System.out.println(prefix + "    " + file.getPath() + " - dir");
	                }
	            } else {
	                System.out.println(prefix + "    " + file.getPath() + " - file");
	            }
	        }
	    }
	}

	@Test
	@Disabled
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());
		
		
	}
	@Test
//	@Disabled
	void printDirectoryFilesTest() throws IOException {
		//TODO testing printDirectoryFiles
		printDirectoryFiles(".", -3);
	}
	void printDirectoryFiles(String path, int maxLevel) throws IOException {
		//TODO based on class Files
		//path -directory path
		//maxLevel - maximal level of printing, if maxLevel < 1, all levels should be printed
		//output format
		//  <directory name (no points, no full absolute path)
		//     <node name> - dir | file
		//          <node_name> .....
		//     <node name> -
		//          <node name> - dir | file
		//                <node_name> .....
		//     <node name> -
		Path directory = Path.of(path);
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}
		Files.walk(directory, maxLevel)
		.forEach(file -> System.out.printf("%s%s - %s\n", getOffset(file), file.getFileName(), dirOrFile(file)));
	}
	
	private String getOffset(Path file) {
		return " ".repeat(file.getNameCount() * 4);
	}

	private String dirOrFile(Path file) {
		return Files.isDirectory(file) ? "dir" : "file";
	}

}