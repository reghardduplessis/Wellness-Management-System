package LibrarySystem.model;

public class Feedback {
    private int id;
    private String student;
    private int rating;
    private String comments;

    public Feedback(int id, String student, int rating, String comments) {
        this.id = id;
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    public Feedback(String student, int rating, String comments) {
        this(0, student, rating, comments);
    }

    // Getters and setters...
}
