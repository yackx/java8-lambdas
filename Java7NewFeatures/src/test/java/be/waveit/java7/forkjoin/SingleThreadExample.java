package be.waveit.java7.forkjoin;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SingleThreadExample {

	
	public static long execute(String path){
			return computeSize(Paths.get(path));
	}
	
	
	private static long computeSize(Path path) {
		long size = 0;
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
	        for (Path entry : stream) {
	            if (Files.isDirectory(entry)) {
	            	size += computeSize(entry);
	            }
	            else {
	            	size += Files.size(entry);
	            }
	            
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    return size;
	}
}
