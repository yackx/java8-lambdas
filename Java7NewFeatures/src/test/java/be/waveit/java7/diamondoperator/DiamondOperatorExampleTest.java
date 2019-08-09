package be.waveit.java7.diamondoperator;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiamondOperatorExampleTest {

	@Test
	public void testJava6(){
		Map<String, List<Node>> trades = new HashMap<String, List<Node>> ();
	}

	@Test
	public void testJava7Diamond(){
		Map<String, List<Node>> trades = new HashMap<> ();
	}

	private class Node {
		// empty
	}
}
