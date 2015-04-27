package be.waveit.java7.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.Test;

public class WatchServiceExampleTest {

	@Test
	public void test_watchService() throws InterruptedException, IOException{
		WatcherThread watcherThread = new WatcherThread();
		watcherThread.start();
		
		Thread.sleep(1000);
		
		Path filePath = Paths.get("./src/test/resources", "test.txt");
		
		System.out.println("Creating file test.txt ...");
		Files.createFile(filePath);
		
		Thread.sleep(1000);
		
		System.out.println("Deleting test.txt ...");
		Files.delete(filePath);
		Thread.sleep(1000);
		
		watcherThread.setRunning(false);
	}	
	
}

class WatcherThread extends Thread{
	
	private boolean running = true;

	@Override
	public void run() {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			
			final Path dir = Paths.get("./src/test/resources");
			
			WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
			
			while (running){
				try {
					key = watchService.take();
					if (key != null) {
						for (WatchEvent<?> event : key.pollEvents()) {
							Path name = (Path) event.context();
							System.out.format(event.kind() + " " + "%s\n", name);
						}
					}
					key.reset();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	


	
}
