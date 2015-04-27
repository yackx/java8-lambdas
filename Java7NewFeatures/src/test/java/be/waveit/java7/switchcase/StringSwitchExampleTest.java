package be.waveit.java7.switchcase;

import org.junit.Test;

public class StringSwitchExampleTest {

	@Test
	public void test_java6() {
		String artist = "David Bowie";
		if (artist.equals("Michael Jackson")){
			System.out.println("I'm a pop singer.");
			
		}else if (artist.equals("David Bowie")){
			System.out.println("I'm a rock singer.");
			
		}else if (artist.equals("Frank Sinatra")){
			System.out.println("I'm a jazz singer.");
			
		}else{
			System.out.println("I'm not a great singer.");
		}
	}
	
	@Test
	public void test_java7() {
		String artist = "David Bowie";
		switch (artist) {
		
		case "Michael Jackson":
			System.out.println("I'm a pop singer.");
			break;
			
		case "David Bowie":
			System.out.println("I'm a rock singer.");
			break;
			
		case "Frank Sinatra":
			System.out.println("I'm a jazz singer.");
			break;
			
		default:
			System.out.println("I'm not a great singer.");
			break;
		}
	}

}
