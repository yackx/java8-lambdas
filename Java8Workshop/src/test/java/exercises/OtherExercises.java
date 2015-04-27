package exercises;

import lambdas.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @Test @Ignore
    public void studentsByYear() {
        // Regroupez les étudiants par années de diplôme
        // dans une map dont les clés sont les années et les valeurs,
        // les listes d'étudiants correspondant à l'année.

        Map<Integer, List<Student>> map = null; // TODO

        Assert.assertEquals(3, map.size());
        Assert.assertEquals(4, map.get(2014).size());
        Assert.assertEquals(3, map.get(2013).size());
        Assert.assertEquals(1, map.get(2015).size());
    }

    @Test @Ignore
    public void studentsSorted() {
        // Triez les étudiants par ordre de résultat décroissant.
        // En cas d'ex-aequo, triez par ordre alphabétique

        List<Student> sorted = null; // TODO

        Assert.assertEquals(students.size(), sorted.size());
        Assert.assertEquals("S2013-18", sorted.get(0).getName());
        Assert.assertEquals("S2014-18", sorted.get(1).getName());
        Assert.assertEquals("S2015-17", sorted.get(2).getName());
        Assert.assertEquals("S2014-11", sorted.get(7).getName());
    }

    @Test @Ignore
    public void findOneBest() {
        // Retrouvez un étudiant qui a obtenu le meilleur score.

        Student best = null; // TODO

        Assert.assertEquals(18, best.getScore());
    }

    @Test @Ignore
    public void concatenate() {
        // Concaténez les chaînes de caractères suivantes:

        List<String> strings = Arrays.asList("ABC", "DEF", "GHI");

        String word = null; // TODO

        Assert.assertEquals("ABCDEFGHI", word);
    }

    @Test @Ignore
    public void addOneToTen() {
        // Additionnez les nombres de 1 à 10.

        int sum = 0; // TODO

        Assert.assertEquals(1+2+3+4+5+6+7+8+9+10, sum);
    }

    @Test @Ignore
    public void multOneToTen() {
        // Multipliez les nombres de 1 à 10.

        int mult = 0; // TODO

        Assert.assertEquals(1*2*3*4*5*6*7*8*9*10, mult);
    }

}
