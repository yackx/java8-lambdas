package be.waveit.java7.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

public class WatchServiceExampleTest {

	@Test
	public void test_watchService() throws InterruptedException, IOException{
		WatcherThread watcherThread = new WatcherThread();
		watcherThread.start();

		Thread.sleep(1000);

		Path filePath = Paths.get("./src/test/resources", "test.txt");

		System.out.println("Creating file test.txt");
		Files.createFile(filePath);

		Thread.sleep(1000);

		System.out.println("Deleting test.txt");
		Files.delete(filePath);
		Thread.sleep(1000);

		watcherThread.setRunning(false);
	}
}

class WatcherThread extends Thread {

	private boolean running = true;

	@Override
	public void run() {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();

			while (running){
				try {
					WatchKey key = watchService.take();
					if (key != null) {
						for (WatchEvent<?> event : key.pollEvents()) {
							Path name = (Path) event.context();
							System.out.format(event.kind() + " " + "%s\n", name);
						}
					}
					key.reset();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void setRunning(boolean running) {
		this.running = running;
	}

}
