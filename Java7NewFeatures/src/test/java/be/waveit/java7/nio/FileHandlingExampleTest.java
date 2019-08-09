package be.waveit.java7.nio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;


public class FileHandlingExampleTest {
    private final static File SOURCE_FILE = new File("./src/test/resources/filehandling/input.txt");
    private final static File TARGET_FILE = new File("./src/test/resources/filehandling/output.txt");

    @Before
    public void cleanBeforeTest() {
        if (TARGET_FILE.exists()) {
            TARGET_FILE.delete();
        }
    }

    @Test
    public void testCopyFileJava6() throws IOException {
        try (InputStream is = new FileInputStream(SOURCE_FILE); OutputStream os = new FileOutputStream(TARGET_FILE)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }

        Assert.assertTrue(TARGET_FILE.exists());
    }

    @Test
    public void testCopyFileJava7() throws IOException {
        Files.copy(SOURCE_FILE.toPath(), TARGET_FILE.toPath());
        Assert.assertTrue(TARGET_FILE.exists());
    }

    @Test
    public void testReadFileJava6() {
        FileReader fr = null;
        BufferedReader reader;
        try {
            fr = new FileReader(SOURCE_FILE);
            reader = new BufferedReader(fr);
            String line;
            line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testReadSmallFileJava7() {
        try {
            List<String> lines = Files.readAllLines(SOURCE_FILE.toPath(), Charset.forName("UTF-8"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadLargeFileJava7() {
        try (BufferedReader reader = Files.newBufferedReader(SOURCE_FILE.toPath(), Charset.forName("UTF-8"))) {
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteFileJava6() {
        try (PrintWriter writer = new PrintWriter(TARGET_FILE, "UTF-8")) {
            writer.println("Some text");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Assert.assertTrue(TARGET_FILE.exists());
    }


    @Test
    public void testWriteFileJava7() {
        try {
            Files.write(TARGET_FILE.toPath(),
                    "Some text".getBytes(),
                    StandardOpenOption.CREATE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Assert.assertTrue(TARGET_FILE.exists());
    }

    @Test
    public void testFileFilter() {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt"); //regex can also be used
        Path path = SOURCE_FILE.toPath();

        if (matcher.matches(path)) {
            System.out.println(path);
        }
    }
}
