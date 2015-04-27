package be.waveit.java7.errorhandling;


public class RethrowingExceptionExampleTest {
	
	  static class FirstException extends Exception { }
	  static class SecondException extends Exception { }

//	  @Test
	  public void test_rethrowExceptionJava6() throws Exception {
		    try {
				throwException();
			} catch (Exception e) {
				throw e;
			}
	  }
	  
//	  @Test
	  public void test_rethrowExceptionJava7() throws FirstException, SecondException{
		    try {
				throwException();
			} catch (Exception e) {
				throw e;
			}
	  }
	  
	  
	  private void throwException() throws FirstException, SecondException{
		  double i = Math.random();
		  if (i < 0.5){
			  throw new FirstException();
		  }else{
			  throw new SecondException();
		  }
	  }
}
