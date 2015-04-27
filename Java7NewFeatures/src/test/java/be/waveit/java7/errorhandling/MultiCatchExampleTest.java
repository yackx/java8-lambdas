package be.waveit.java7.errorhandling;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

public class MultiCatchExampleTest {

	@Test
	public void test_multipleExceptionsJava6(){
		try {
			generateExceptions();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_multipleExceptionsJava7(){
		try {
			generateExceptions();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void generateExceptions() throws IOException, SQLException{
		throw new IOException();
	}
	
}
