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

    @Test
    public void studentsByYear() {
        // Regroupez les étudiants par années de diplôme
        // dans une map dont les clés sont les années et les valeurs,
        // les listes d'étudiants correspondant à l'année.

        Map<Integer, List<Student>> map = students.stream()
                .collect(groupingBy(Student::getGradYear));

        Assert.assertEquals(3, map.size());
        Assert.assertEquals(4, map.get(2014).size());
        Assert.assertEquals(3, map.get(2013).size());
        Assert.assertEquals(1, map.get(2015).size());
    }

    @Test
    public void studentsSorted() {
        // Triez les étudiants par ordre de résultat décroissant.
        // En cas d'ex-aequo, triez par ordre alphabétique

        List<Student> sorted = students.stream()
                .sorted(comparing(Student::getScore).reversed().thenComparing(Student::getName))
                .collect(toList());

        Assert.assertEquals(students.size(), sorted.size());
        Assert.assertEquals("S2013-18", sorted.get(0).getName());
        Assert.assertEquals("S2014-18", sorted.get(1).getName());
        Assert.assertEquals("S2015-17", sorted.get(2).getName());
        Assert.assertEquals("S2014-11", sorted.get(7).getName());
    }

    @Test
    public void findOneBest() {
        // Retrouvez un étudiant qui a obtenu le meilleur score.

        Student best = students.stream()
        		.max(comparingInt(Student::getScore))
        		.get();

        Assert.assertEquals(18, best.getScore());
    }

    @Test
    public void concatenate() {
        // Concaténez les chaînes de caractères suivantes:

        List<String> strings = Arrays.asList("ABC", "DEF", "GHI");

        String word = strings.stream().collect(joining());

        Assert.assertEquals("ABCDEFGHI", word);
    }

    @Test
    public void addOneToTen() {
        // Additionnez les nombres de 1 à 10.

        int sum = IntStream.rangeClosed(1, 10).sum();

        Assert.assertEquals(1+2+3+4+5+6+7+8+9+10, sum);
    }

    @Test
    public void multOneToTen() {
        // Multipliez les nombres de 1 à 10.

        int mult = IntStream.rangeClosed(1, 10).reduce(1, (a, b) -> a * b);

        Assert.assertEquals(1*2*3*4*5*6*7*8*9*10, mult);
    }

    @Test
    public void fibonacci() {
        // Créez un stream infini conforme à la suite de Fibonacci
        // dont vous allez extraire les dix premières valeurs
        // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...

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
