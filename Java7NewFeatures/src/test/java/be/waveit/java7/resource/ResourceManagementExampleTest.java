package be.waveit.java7.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceManagementExampleTest {

    @Mock
    private OutputStreamFactory factory;

    @Mock
    private FileOutputStream stream;

    @Before
    public void initMocks() throws FileNotFoundException {
        MockitoAnnotations.initMocks(this);
        when(factory.create(any(String.class))).thenReturn(stream);
    }

    @Test
    public void testStreamResourceJava6() throws IOException {

        FileOutputStream out = null;
        try {
            out = factory.create("output.txt"); //= new FileOutputStream("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        verify(stream, times(1)).close();
    }

    @Test
    public void testStreamResourceJava7() throws IOException {

        try (FileOutputStream out = factory.create("output.txt");) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        verify(stream, times(1)).close();
    }


    private interface OutputStreamFactory {
        FileOutputStream create(String filename) throws FileNotFoundException;
    }
}
