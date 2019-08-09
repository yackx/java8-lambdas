package solutions;

import lambdas.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OtherExercises {

    private Collection<Student> students;

    @Before
    public void setUp() {
        students = Arrays.asList(
                new Student("S2014-11", 2014, 11),
                new Student("S2014-18", 2014, 18),
                new Student("S2013-18", 2013, 18),
                new Student("S2013-16", 2013, 16),
                new Student("S2014-11", 2014, 11),
                new Student("S2013-12", 2013, 12),
                new Student("S2015-17", 2015, 17),
                new Student("S2014-15", 2014, 15)
        );
    }

    // Group students by graduation years.
    // Key is the year and value is the list of students of that year.
    @Test
    public void studentsByYear() {
        Map<Integer, List<Student>> map = students.stream()
                .collect(groupingBy(Student::getGradYear));

        Assert.assertEquals(3, map.size());
        Assert.assertEquals(4, map.get(2014).size());
        Assert.assertEquals(3, map.get(2013).size());
        Assert.assertEquals(1, map.get(2015).size());
    }

    // Sort students by decreasing results.
    // In case of identical result, sort alphabetically.
    @Test
    public void studentsSorted() {
        List<Student> sorted = students.stream()
                .sorted(comparing(Student::getScore).reversed().thenComparing(Student::getName))
                .collect(toList());

        Assert.assertEquals(students.size(), sorted.size());
        Assert.assertEquals("S2013-18", sorted.get(0).getName());
        Assert.assertEquals("S2014-18", sorted.get(1).getName());
        Assert.assertEquals("S2015-17", sorted.get(2).getName());
        Assert.assertEquals("S2014-11", sorted.get(7).getName());
    }

    // Find one student that has obtained the highest score.
    @Test
    public void findOneBest() {
        Student best = students.stream()
        		.max(comparingInt(Student::getScore))
        		.get();

        Assert.assertEquals(18, best.getScore());
    }

    // Concatenate the given strings.
    @Test
    public void concatenate() {
        List<String> strings = Arrays.asList("ABC", "DEF", "GHI");

        String word = strings.stream().collect(joining());

        Assert.assertEquals("ABCDEFGHI", word);
    }

    // Sum up integer numbers from 1 to 10.
    @Test
    public void addOneToTen() {
        int sum = IntStream.rangeClosed(1, 10).sum();

        Assert.assertEquals(1+2+3+4+5+6+7+8+9+10, sum);
    }

    // Multiply integer numbers from 1 to 10.
    @Test
    public void multOneToTen() {
        int mult = IntStream.rangeClosed(1, 10).reduce(1, (a, b) -> a * b);

        Assert.assertEquals(1*2*3*4*5*6*7*8*9*10, mult);
    }

    // Create an infinite stream matching the Fibonacci suite.
    // Extract the 10 first values of that streams.
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
    @Test
    public void fibonacci() {
        LongStream fibs = Stream.iterate(
                new long[] {0, 1, 1},
                f -> new long[] {f[1], f[0] + f[1]}
        ).mapToLong(f -> f[0]);

        List<Long> f = fibs.limit(10).boxed().collect(toList());
        Assert.assertEquals(0L, (long)f.get(0));
        Assert.assertEquals(1L, (long)f.get(1));
        Assert.assertEquals(1L, (long)f.get(2));
        Assert.assertEquals(2L, (long)f.get(3));
        Assert.assertEquals(13L, (long)f.get(7));
        Assert.assertEquals(21L, (long)f.get(8));
        Assert.assertEquals(34L, (long) f.get(9));
    }
}
