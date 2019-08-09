package be.waveit.java7.forkjoin;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.text.DecimalFormat;

public class ForkJoinPerformanceTest {

	@Test
	public void test_smallDirectory(){
		String path = "."; //Project directory


        long startTime = System.currentTimeMillis();
        long totalSize1 = ForkJoinExample.execute(path);
	    long endTime = System.currentTimeMillis();

	    System.out.println("ForkJoin example");
	    System.out.println("Total siz : " + formatSize(totalSize1));
	    System.out.println("Processing time: " + (endTime-startTime) + " ms");

	    startTime = System.currentTimeMillis();
        long totalSize2 = SingleThreadExample.execute(path);
	    endTime = System.currentTimeMillis();

	    System.out.printf("Single Thread example" +
				"%nTotal size: " + formatSize(totalSize2) +
				"%nProcessing time : " + (endTime-startTime) +" ms%n");

	    Assert.assertEquals(totalSize2, totalSize1);
	}

	@Ignore
	@Test
	public void test_largeDirectory() {
		String path = "C:\\Data"; // large directory

		long startTime = System.currentTimeMillis();
		long totalSize1 = ForkJoinExample.execute(path);
		long endTime = System.currentTimeMillis();

		System.out.println("ForkJoin example");
		System.out.println("Total siz : " + formatSize(totalSize1));
		System.out.println("Processing time: " + (endTime - startTime) + " ms");

		startTime = System.currentTimeMillis();
		long totalSize2 = SingleThreadExample.execute(path);
		endTime = System.currentTimeMillis();

		System.out.printf("Single Thread example" +
				"%nTotal size: " + formatSize(totalSize2) +
				"%nProcessing time : " + (endTime-startTime) +" ms%n");

		Assert.assertEquals(totalSize2, totalSize1);
	}

	private static String formatSize(Long totalSize) {
		DecimalFormat df = new DecimalFormat("0.00");
		float sizeKb = 1024.0f;
	    float sizeMo = sizeKb * sizeKb;
	    float sizeGo = sizeMo * sizeKb;
	    float sizeTerra = sizeGo * sizeKb;

	    if (totalSize < sizeMo)
	        return df.format(totalSize / sizeKb)+ " Kb";
	    else if (totalSize < sizeGo)
	        return df.format(totalSize / sizeMo) + " Mo";
	    else if (totalSize < sizeTerra)
	        return df.format(totalSize / sizeGo) + " Go";

	    return "";
	}
}
