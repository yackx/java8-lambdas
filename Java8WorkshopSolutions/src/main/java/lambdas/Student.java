package lambdas;

public final class Student {

    private final String name;
    private final int graduationYear;
    private final int score;

    public Student(final String name, final int graduationYear, final int score) {
        this.name = name;
        this.graduationYear = graduationYear;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public int getScore() {
        return score;
    }
}
