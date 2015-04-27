package be.waveit.java7.diamondoperator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DiamondOperatorExampleTest {
	
	@Test
	public void test_java6Example(){
		Map<String, List<Node>> trades = new HashMap<String, List<Node>> ();
		System.out.println(trades.size());
	}
	
	@Test
	public void test_java7Example(){
		Map<String, List<Node>> trades = new HashMap<> ();
		System.out.println(trades.size());
	}
	
	private class Node {
		
	}

}
