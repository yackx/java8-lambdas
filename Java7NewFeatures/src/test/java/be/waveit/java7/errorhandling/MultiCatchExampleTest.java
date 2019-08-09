package be.waveit.java7.errorhandling;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class MultiCatchExampleTest {

	@Test
	public void testCatchMultipleExceptionsJava6(){
		try {
			generateExceptions();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCatchMultipleExceptionsJava7(){
		try {
			generateExceptions();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void generateExceptions() throws IOException, SQLException {
		throw new IOException();
	}
}
