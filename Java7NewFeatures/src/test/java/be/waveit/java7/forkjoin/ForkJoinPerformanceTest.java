package be.waveit.java7.forkjoin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;







import org.junit.Assert;
import org.junit.Test;

public class ForkJoinPerformanceTest {

	
	@Test
	public void test_smallDirectory(){
		String path = "."; //Project directory
		
	        
        long startTime = System.currentTimeMillis();
        long totalSize1 = ForkJoinExample.execute(path);
	    long endTime = System.currentTimeMillis();
	    
	    System.out.println("ForkJoin example -  Total size : " + formatSize(totalSize1) + " , processing time : " + (endTime-startTime) +" ms.");
	    
	    startTime = System.currentTimeMillis();
        long totalSize2 = SingleThreadExample.execute(path);
	    endTime = System.currentTimeMillis();
	        
	    System.out.println("Single Thread example -  Total size : " + formatSize(totalSize2) + " , processing time : " + (endTime-startTime) +" ms.");
	    
	    Assert.assertEquals(totalSize2, totalSize1);
		
	}
	
	
	@Test
	public void test_largeDirectory() {
		String path = "C:\\Data"; // large directory

		long startTime = System.currentTimeMillis();
		long totalSize1 = ForkJoinExample.execute(path);
		long endTime = System.currentTimeMillis();

		System.out.println("ForkJoin example -  Total size : " + formatSize(totalSize1) + " , processing time : " + (endTime-startTime) +" ms.");

		startTime = System.currentTimeMillis();
		long totalSize2 = SingleThreadExample.execute(path);
		endTime = System.currentTimeMillis();

		System.out.println("Single Thread example -  Total size : " + formatSize(totalSize2) + " , processing time : " + (endTime-startTime) +" ms.");;

		Assert.assertEquals(totalSize2, totalSize1);

	}
	
	static String formatSize(Long totalSize) {
		DecimalFormat df = new DecimalFormat("0.00");
		float sizeKb = 1024.0f;
	    float sizeMo = sizeKb * sizeKb;
	    float sizeGo = sizeMo * sizeKb;
	    float sizeTerra = sizeGo * sizeKb;

	    if(totalSize < sizeMo)
	        return df.format(totalSize / sizeKb)+ " Kb";
	    else if(totalSize < sizeGo)
	        return df.format(totalSize / sizeMo) + " Mo";
	    else if(totalSize < sizeTerra)
	        return df.format(totalSize / sizeGo) + " Go";

	    return "";
	}
	
}
