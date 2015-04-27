package be.waveit.java8.fibonacci;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FibonacciTest {

    @Test
    public void fibonacci() {
        // Créez un stream infini conforme à la suite de Fibonacci
        // dont vous allez extraire les dix premières valeurs
        // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...

        LongStream fibs = Stream.iterate(
                new long[]{0, 1, 1},
                f -> new long[]{f[1], f[0] + f[1]}
        ).mapToLong(f -> f[0]);

        List<Long> f = fibs.limit(10).boxed().collect(toList());
        Assert.assertEquals(0L, (long) f.get(0));
        Assert.assertEquals(1L, (long)f.get(1));
        Assert.assertEquals(1L, (long)f.get(2));
        Assert.assertEquals(2L, (long)f.get(3));
        Assert.assertEquals(13L, (long)f.get(7));
        Assert.assertEquals(21L, (long)f.get(8));
        Assert.assertEquals(34L, (long) f.get(9));
    }
}
