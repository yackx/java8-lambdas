package be.waveit.java7.literals;

import org.junit.Assert;
import org.junit.Test;

public class LiteralWithUnderscoresExampleTest {

	
	@Test
	public void test(){
		int millionJava6 = 1000000;
		int millionJava7 = 1_000_000;
		Assert.assertEquals("Million expected.", millionJava7 , millionJava6);
	}
	
}
