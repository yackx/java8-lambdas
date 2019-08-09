package lambdas;

public final class Student {

    private final String name;
    private final int gradYear;
    private final int score;

    public Student(final String name, final int gradYear, final int score) {
        this.name = name;
        this.gradYear = gradYear;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getGradYear() {
        return gradYear;
    }

    public int getScore() {
        return score;
    }
}
