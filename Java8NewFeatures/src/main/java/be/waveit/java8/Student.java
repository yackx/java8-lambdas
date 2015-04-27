package be.waveit.java8;

public class Student {

    private String name;
    private int gradYear;
    private int score;

    public Student(String name, int gradYear, int score) {
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
