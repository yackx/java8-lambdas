package be.waveit.java7.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FileHandlingExampleTest {
	
	private final static File SOURCE_FILE = new File("./src/test/resources/filehandling/input.txt");
	
	private final static File TARGET_FILE = new File("./src/test/resources/filehandling/output.txt");

	@Before
	public void cleanBeforeTest(){
		if (TARGET_FILE.exists()){
			TARGET_FILE.delete();
		}
	}
	
	@Test
	public void test_copyFileJava6() throws IOException{
		    InputStream is = null;
		    OutputStream os = null;
		    try {
		        is = new FileInputStream(SOURCE_FILE);
		        os = new FileOutputStream(TARGET_FILE);
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		    } finally {
		        is.close();
		        os.close();
		    }
		    
		    Assert.assertTrue(TARGET_FILE.exists());
	}
	
	
	@Test
	public void test_copyFileJava7() throws IOException{
		Files.copy(SOURCE_FILE.toPath(), TARGET_FILE.toPath());
		Assert.assertTrue(TARGET_FILE.exists());
	}
	
	
	@Test
	public void test_readFileJava6(){
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader(SOURCE_FILE);
			reader = new BufferedReader(fr);
			String line;
			line = reader.readLine();
			while ( line != null ) {
				System.out.println(line);
			    line = reader.readLine();
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}  finally{
			if (fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Test
	public void test_readSmallFileJava7(){
		try {
			List<String> lines = Files.readAllLines(SOURCE_FILE.toPath(), Charset.forName("UTF-8"));
			for (String line : lines){
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_readLargeFileJava7(){
		try (BufferedReader reader = Files.newBufferedReader(SOURCE_FILE.toPath(), Charset.forName("UTF-8"));){
			String line = reader.readLine();
			while ( line != null ) {
				System.out.println(line);
			    line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_writeFileJava6(){
		PrintWriter writer = null; 
		try {
			writer = new PrintWriter(TARGET_FILE, "UTF-8");
			writer.println("Some text");
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace();
        }finally{
        	if (writer != null) writer.close();
        }
		Assert.assertTrue(TARGET_FILE.exists());
	}
	
	
	@Test
	public void test_writeFileJava7(){
		try {
            Files.write( TARGET_FILE.toPath(),
                         "Some text".getBytes(),
                         StandardOpenOption.CREATE);
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace();
        }
		Assert.assertTrue(TARGET_FILE.exists());
	}
	
	@Test
	public void test_fileFilter(){
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt"); //regex can also be used
		Path path = SOURCE_FILE.toPath();
	
		if (matcher.matches(path)) {
			System.out.println(path);
		}
	}
	
	
	
}
